package cl.hda.regapp.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import cl.hda.regapp.entities.CabAplicacion;
import cl.hda.regapp.entities.Conversion;
import cl.hda.regapp.entities.DetAplicacion;
import cl.hda.regapp.entities.Producto;
import cl.hda.regapp.entities.Sector;
import cl.hda.regapp.entities.TabCod;
import cl.hda.regapp.model.CabAplicacionDataModel;
import cl.hda.regapp.model.DetAplicacionDataModel;
import cl.hda.regapp.repository.CabAplicacionRepository;
import cl.hda.regapp.repository.ConversionRepository;
import cl.hda.regapp.repository.DetAplicacionRepository;
import cl.hda.regapp.repository.ProductoRepository;
import cl.hda.regapp.repository.SectorRepository;
import cl.hda.regapp.repository.TabCodRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


@Named("formCab")
@SessionScope
public class FormCabController {
  
	
  String titulo;
  
  @Autowired
  private EntityManager entityManager;
  
  @Inject
  ListarCabController listarCabController;
	
  @Autowired
  SectorRepository sectorRepository;
  
  @Autowired
  ProductoRepository productoRepository;
  
  @Autowired
  TabCodRepository tabCodRepository;
  
  
  @Autowired
  CabAplicacionRepository cabAplicacionRepository;
  
  @Autowired
  DetAplicacionRepository detAplicacionRepository;
  
  @Autowired
  ConversionRepository conversionRepository;
  
  
  
  DetAplicacion detAplicacion;
  
  SelectOneMenu temporadaComponent;
  UIComponent inicioComponent;
  UIComponent productoComponent;
  
  private Sector sectorSel  = new Sector();
  CabAplicacion cabApp      = new CabAplicacion();
  List<DetAplicacion> lstDetAplicacion = new ArrayList<>();
  List<DetAplicacion> lstDetAplicacionDel = new ArrayList<>();
  List<Producto> productos  = new ArrayList<>();
  List<Sector> sectores     = new ArrayList<>();
  List<TabCod> lstObjetivos = new ArrayList<>();
  List<TabCod> lstMetodos   = new ArrayList<>();
  List<TabCod> lstMaquinas  = new ArrayList<>();
  List<TabCod> lstUmedida   = new ArrayList<>();
  List<TabCod> lstUmedidaGasto = new ArrayList<>();
  List<TabCod> lstTemporadas   = new ArrayList<>();
  List<Conversion> lstConversion = new ArrayList<>();
  DetAplicacionDataModel  detAplicacionDataModel = new DetAplicacionDataModel(new ArrayList<DetAplicacion>());
  
  Producto productoSel;
  Integer temporadaSel;
  
  
  DetAplicacion detSelected;
  
  private static final Logger logger = LoggerFactory.getLogger(FormCabController.class);

  Date fechaActual =new Date();
  
  DetAplicacion detAplicacionAux= new DetAplicacion();
  
  @PersistenceContext
  private EntityManager em;
  
  
  public void cambioTemporada() {
	  
	Integer temporada = cabApp.getTemporada() ;
	
	sectores = sectorRepository.findByTemporada(temporada);
	productos = productoRepository.findByTemporada(temporada);
	
	 
	
	  
	  
  }
  
  
  
  public String cancelar(){
	  
	 cabApp.getDetAplicacion().addAll(lstDetAplicacionDel);
     return "ListarCab?faces-redirect=true"; 

  }
  
  
  @Transactional
  public void eliminar(Long id){
	  
	  Query q = em.createNativeQuery("delete det_aplicacion where id = ?1");
	  q.setParameter(1, id);
	  q.executeUpdate();
  }
  
  
  
  @PostConstruct
  public void init(){
	  
	  cargarProductos();
	  cargarSectores();
	  cargarParametros();
	 
	  detAplicacionDataModel = new DetAplicacionDataModel(lstDetAplicacion);
	  lstConversion          = (List<Conversion>) conversionRepository.findAll();
  }

  
  public void metodoListener(ValueChangeEvent event){
	  
      lstUmedida   = tabCodRepository.findByCodigo("unidad_medida");
	  
	  if(!event.getNewValue().toString().equals("VÍA RIEGO"))
	     lstUmedida = lstUmedida.stream().filter(x->{ if ( x.getValor().equals("CC/HL") || x.getValor().equals("GR/HL") ) return true;else return false;}).collect(Collectors.toList());
	  else
	    lstUmedida = lstUmedida.stream().filter(x->{ if ( x.getValor().equals("LT/HA") || x.getValor().equals("KG/HA") ) return true;else return false;}).collect(Collectors.toList());

	  logger.info(lstUmedida.size()+" regs ");
	  cabApp.setMetodo(event.getNewValue().toString());
	  
  }
  
  public String salvar(){
	  
	  FacesContext facesContext = FacesContext.getCurrentInstance();
	  Flash flash = facesContext.getExternalContext().getFlash();
	  flash.setKeepMessages(true);
	  	  
     try{
    	
    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
 		 String usuario = authentication.getName();
    	 List<DetAplicacion> lst = new ArrayList<>();
 		 
    	 if (detAplicacionDataModel.getLstCosechaDet().size() == 0)
    		 throw new Exception("Debe existir al menos un detalle");
    	 
    	 
 		 for(DetAplicacion x : detAplicacionDataModel.getLstCosechaDet()){
 			  
 			 logger.info("producto "+x.getProducto().getProducto());
			  
   		     if(titulo.equals("Nuevo Registro")){
   		  		  x.setId(null);
			      x.setCabAplicacion(cabApp);

   		     }else{
   			    if(x.getId() == -1){
   				  x.setId(null);
   			      x.setCabAplicacion(cabApp);
   			    }
   		    }
   		  
			      
		    	 if(!cabApp.getMetodo().equals("VÍA RIEGO")){
				       x.setGastoTotal( (double) ((cabApp.getMojamiento()*x.getDosis()/100)/1000) );
	     		   	   x.setMojamientoReal( (double) (cabApp.getMojamiento()/cabApp.getSector().getSuperficieReal()) );
		    	  }else{
		    		   cabApp.setMojamiento(null);
		    		   cabApp.setUmMojamiento(null);
		    		   cabApp.setMaquina(null);
		    		   x.setGastoTotal(null);
		    		   x.setMojamientoReal(null);
		    	   }
		     	    
				x.setFechaViableCosecha(sumarRestarDiasFecha(cabApp.getFecha(), x.getCarencia()));
		   	    x.setFechaViableCosecha(sumarRestarDiasFecha(cabApp.getFecha(), x.getCarencia()));
		        lst.add(x);
 		   }
 		
 		  cabApp.setDetAplicacion(lst);
		  cabApp.setUsuario(usuario);
		  cabAplicacionRepository.saveAndFlush(cabApp);
		  lstDetAplicacionDel= new ArrayList<>();	  
		  facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se grabo Correctamente", null));
		  cabApp = new CabAplicacion();
		  detAplicacionDataModel = new DetAplicacionDataModel(new ArrayList<DetAplicacion>());
		  listarCabController.obtener();
		  return "ListarCab?faces-redirect=true"; 
     }catch(Exception e){
		  
		  facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		     return "FormCab?faces-redirect=true"; 
      }
	
  }
  

  public Date sumarRestarDiasFecha(Date fecha, int dias){
	  
	  java.util.Calendar calendar = java.util.Calendar.getInstance();
	  calendar.setTime(fecha); // Configuramos la fecha que se recibe
	  calendar.add(java.util.Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
	  return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
  }
  
  

public void addRow(){
	
	
	System.out.println("agregando fila");
	
	DetAplicacion det = new DetAplicacion();
	
	det.setId(-1L);
	det.setHoraInicio(LocalDateTime.now());
	det.setHoraTermino(LocalDateTime.now());
	det.setProducto(productoSel);
	detAplicacionDataModel.getLstCosechaDet().add(det);
	  	
    FacesMessage msg = new FacesMessage("Nuevo Registro agregado");
    FacesContext.getCurrentInstance().addMessage(null, msg);
	
	
}
  
  
public CabAplicacion getCabApp() {
	return cabApp;
}


public void setCabApp(CabAplicacion cabApp) {
	this.cabApp = cabApp;
}


public List<Sector> getSectores() {
	return sectores;
}


public void setSectores(List<Sector> sectores) {
	this.sectores = sectores;
}


public Sector getSectorSel() {
	return sectorSel;
}


public void setSectorSel(Sector sectorSel) {
	this.sectorSel = sectorSel;
}


public List<DetAplicacion> getLstDetAplicacion() {
	return lstDetAplicacion;
}


public void setLstDetAplicacion(List<DetAplicacion> lstDetAplicacion) {
	this.lstDetAplicacion = lstDetAplicacion;
}


public List<Producto> getProductos() {
	return productos;
}


public void setProductos(List<Producto> productos) {
	this.productos = productos;
}


public List<TabCod> getLstObjetivos() {
	return lstObjetivos;
}


public void setLstObjetivos(List<TabCod> lstObjetivos) {
	this.lstObjetivos = lstObjetivos;
}


public List<TabCod> getLstMetodos() {
	return lstMetodos;
}


public void setLstMetodos(List<TabCod> lstMetodos) {
	this.lstMetodos = lstMetodos;
}


public List<TabCod> getLstMaquinas() {
	return lstMaquinas;
}


public void setLstMaquinas(List<TabCod> lstMaquinas) {
	this.lstMaquinas = lstMaquinas;
}


public List<TabCod> getLstUmedida() {
	return lstUmedida;
}


public void setLstUmedida(List<TabCod> lstUmedida) {
	this.lstUmedida = lstUmedida;
}


public List<TabCod> getLstUmedidaGasto() {
	return lstUmedidaGasto;
}


public void setLstUmedidaGasto(List<TabCod> lstUmedidaGasto) {
	this.lstUmedidaGasto = lstUmedidaGasto;
}


public DetAplicacionDataModel getDetAplicacionDataModel() {
	return detAplicacionDataModel;
}


public void setDetAplicacionDataModel(DetAplicacionDataModel detAplicacionDataModel) {
	this.detAplicacionDataModel = detAplicacionDataModel;
}


public DetAplicacion getDetAplicacion() {
	return detAplicacion;
}


public void setDetAplicacion(DetAplicacion detAplicacion) {
	this.detAplicacion = detAplicacion;
}
  
public void onRowEdit(RowEditEvent event) {
    
	detAplicacionAux = (DetAplicacion)event.getObject();
	
}


public Date getFechaActual() {
	return fechaActual;
}


public void setFechaActual(Date fechaActual) {
	this.fechaActual = fechaActual;
}


public DetAplicacion getDetAplicacionAux() {
	return detAplicacionAux;
}


public void setDetAplicacionAux(DetAplicacion detAplicacionAux) {
	this.detAplicacionAux = detAplicacionAux;
}


public UIComponent getInicioComponent() {
	return inicioComponent;
}


public void setInicioComponent(UIComponent inicioComponent) {
	this.inicioComponent = inicioComponent;
}


public DetAplicacion getDetSelected() {
	return detSelected;
}


public UIComponent getProductoComponent() {
	return productoComponent;
}

public void setProductoComponent(UIComponent productoComponent) {
	this.productoComponent = productoComponent;
}

public void setDetSelected(DetAplicacion detSelected) {
	logger.info("aquiii");
	this.detSelected = detSelected;
}


public void eliminarFila(){
    
	
	
	logger.info("hash elimina"+detAplicacion.hashCode());
	List<DetAplicacion> lst = detAplicacionDataModel.getLstCosechaDet();
	
	/** nuevo **/
	lst.removeIf(v->v.hashCode()==detAplicacion.hashCode());
	
	detAplicacionDataModel = new DetAplicacionDataModel(lst);
	if(detAplicacion.getId() != -1){
		  lstDetAplicacionDel.add(detAplicacion);
	}
}


public void onRowCancel(RowEditEvent event){
	
	
	detAplicacionDataModel.getLstCosechaDet().remove(detAplicacionDataModel.getRowIndex());
	
	PrimeFaces.current().ajax().update("form:det1");

	
}


public void cargarProductos(){

	  productos = (List<Producto>)productoRepository.findByVigente(true);

}

public void cargarSectores() {
	// TODO Auto-generated method stub
	  sectores = (List<Sector>) sectorRepository.findAll();

}


public void cargarParametros(){
	 
	 lstObjetivos = tabCodRepository.findByCodigo("objetivo_aplicacion");
	 lstMetodos   = tabCodRepository.findByCodigo("metodo_aplicacion");
	 lstMaquinas  = tabCodRepository.findByCodigo("maquinas");
	 lstTemporadas = tabCodRepository.findByCodigo("temporada");
	 
	 TabCod  metodo  = lstMetodos.get(0);
	 lstUmedida   = tabCodRepository.findByCodigo("unidad_medida");
 
	  
	  if(!metodo.getValor().equals("VÍA RIEGO"))
	     lstUmedida = lstUmedida.stream().filter(x->{ if ( x.getValor().equals("CC/HL") || x.getValor().equals("GR/HL") ) return true;else return false;}).collect(Collectors.toList());
	  else
	    lstUmedida = lstUmedida.stream().filter(x->{ if ( x.getValor().equals("LT/HA") || x.getValor().equals("KG/HA") ) return true;else return false;}).collect(Collectors.toList());
	 
	 lstUmedidaGasto = tabCodRepository.findByCodigo("unidad_medida_gasto");
}










public String getTitulo() {
	return titulo;
}

public void setTitulo(String titulo) {
	this.titulo = titulo;
}


public Producto getProductoSel() {
	return productoSel;
}


public void setProductoSel(Producto productoSel) {
	this.productoSel = productoSel;
}


public List<TabCod> getLstTemporadas() {
	return lstTemporadas;
}


public void setLstTemporadas(List<TabCod> lstTemporadas) {
	this.lstTemporadas = lstTemporadas;
}



public Integer getTemporadaSel() {
	return temporadaSel;
}



public void setTemporadaSel(Integer temporadaSel) {
	this.temporadaSel = temporadaSel;
}



public SelectOneMenu getTemporadaComponent() {
	return temporadaComponent;
}



public void setTemporadaComponent(SelectOneMenu temporadaComponent) {
	this.temporadaComponent = temporadaComponent;
}

 



}