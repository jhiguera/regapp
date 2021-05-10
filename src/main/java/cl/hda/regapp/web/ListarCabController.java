package cl.hda.regapp.web;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.primefaces.model.DefaultStreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import cl.hda.regapp.entities.CabAplicacion;
import cl.hda.regapp.entities.DetAplicacion;
import cl.hda.regapp.entities.Role;
import cl.hda.regapp.entities.User;
import cl.hda.regapp.model.CabAplicacionDataModel;
import cl.hda.regapp.model.DetAplicacionDataModel;
import cl.hda.regapp.model.export;
import cl.hda.regapp.repository.CabAplicacionRepository;
import cl.hda.regapp.repository.DetAplicacionRepository;
import cl.hda.regapp.repository.UserRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Named("listarCab")
@SessionScope
public class ListarCabController {
	
	@Autowired
	EntityManager em;
	
	@Inject
	FormCabController formCabController;
	
	private static final Logger logger = LoggerFactory.getLogger(ListarCabController.class);

	
	@Autowired
	CabAplicacionRepository cabAplicacionRepository;
	
	@Autowired
	DetAplicacionRepository detAplicacionRepository;
	
	
	@Autowired
	UserRepository userRepository;
		
	CabAplicacionDataModel cabAplicacionDataModel;
	CabAplicacion cabAplicacion;


	private boolean isAdmin;


	private String usuario;
	
	@PostConstruct
	@Transactional
	public void init(){
		  		
		  cabAplicacion = new CabAplicacion();
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		  
		  if( authentication != null){
		  usuario = authentication.getName();
 		  User u         = userRepository.findByUsername(usuario);
 		  Set<Role> roles = u.getRoles();
 		  isAdmin = roles.stream().anyMatch(new Predicate<Role>(){

			@Override
			public boolean test(Role t) {
				// TODO Auto-generated method stub
				return t.getName().equals("ROLE_ADMIN");
			}

		});
 		
 	    if(isAdmin)
 		     cabAplicacionDataModel = new CabAplicacionDataModel((List<CabAplicacion>)cabAplicacionRepository.findAll());
 		else
 	 		 cabAplicacionDataModel = new CabAplicacionDataModel((List<CabAplicacion>)cabAplicacionRepository.findByUsuario(usuario));
 		  }
 	}
    
	@Autowired
	ResourceLoader resourceLoader;

	@Autowired
	DataSource dataSource;

	public void generarReporte() throws JRException, IOException, SQLException {
		
		
		 Resource resource = this.resourceLoader.getResource("classpath:/jasper/test.jrxml");
		 InputStream jasperStream = resource.getInputStream();
		 JasperDesign design = JRXmlLoader.load(jasperStream);
		 JasperReport report = JasperCompileManager.compileReport(design);
		      
		 Map<String, Object> param = new HashMap<>();
		 param.put("datasource", this.dataSource.getConnection());
		   
		 JasperPrint jasperPrint = JasperFillManager.fillReport(report, param, this.dataSource.getConnection());
	     
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        ExternalContext externalContext = facesContext.getExternalContext();
	        externalContext.setResponseContentType("application/x-pdf");
	        externalContext.setResponseHeader("Content-disposition", "inline; filename=\"Reporte"   + ".pdf\"");

	        JasperExportManager.exportReportToPdfStream(jasperPrint, (OutputStream)externalContext.getResponseOutputStream());
	        facesContext.responseComplete();

	       
	}

	
	
	
	
	public void export2Excel() throws Throwable {
	
	  String sqlUsuario = "";
	  if(!isAdmin)
		 sqlUsuario     = " and usuario = '"+usuario+"'";
		
	  Query q  = em.createNativeQuery(" select ca.nro_orden ,"
                     +" ca.fecha ,"
                     +" s.sector_productivo ,"
                     +" s.parcela ,"
                     +" s.superficie_real_plantada ,"
                     +" s.especie_plantada ,"
                     +" s.variedad_copa ,"
                     +" s.variedad_portainjerto ,"
                     +" p.producto ,"
                     +" p.caracteristicas ,"
                     +" p.color_etiqueta ,"
                     +" p.ingrediente_activo ,"
                     +" p.mda_irac ,"
                     +" p.mda_frac ,"
                     +" da.objetivo ,"
                     +" da.dosis ,"
                     +" da.um_dosis ,"
                     +" ca.mojamiento ,"
                     +" da.mojamiento_real,"
                     +" ca.um_mojamiento as 'um'  ,"
                     +" da.gasto_total ,"
                     +" ca.um_mojamiento  ,"
                     +" ca.metodo  ,"
                     +" ca.maquina  ,"
                     +" ca.aplicadores ,"
			         +" p.reingreso ,"
			         +" da.carencia ,"
			         +" da.fecha_viable_cosecha ,"
			         +" (da.hora_inicio) as 'hora_inicio',"
			         +" (da.hora_termino) as 'hora_termino'"
			         +" from" 
			         +" cab_aplicacion ca" 
			       +" inner join det_aplicacion da on da.cab_aplicacion_id = ca.id" 
			       +" inner join sector s on s.id = ca.sector_id"  
			       +" inner join producto p on p.id = da.producto_id"
			       + sqlUsuario);
	      
	       List<Object[]> lst = q.getResultList();
	      
		   List<String> columnNames = new ArrayList<String>();
		   columnNames.add("Nro Orden");
		   columnNames.add("Fecha Aplicacion");
		   columnNames.add("Sector");
		   columnNames.add("Parcela");
		   columnNames.add("Superficie");
		   columnNames.add("Especie");
		   columnNames.add("Variedad Copa");
		   columnNames.add("Variedad Porta Injerto");
		   columnNames.add("Producto");
		   columnNames.add("Caracteristica");
		   columnNames.add("Color Etiqueta");
		   columnNames.add("Ingrediente Activo");
		   columnNames.add("Mda Irac");
		   columnNames.add("Mda Frac");
		   columnNames.add("Objectivo");
		   columnNames.add("Dosis");
		   columnNames.add("Md");
		   columnNames.add("Mojamiento Total");
		   columnNames.add("Mojamiento Real/Ha");
		   columnNames.add("Md Mojamiento");
		   columnNames.add("Gasto Total");
		   columnNames.add("Md Gasto");
		   columnNames.add("Metodo Aplicacion");
		   columnNames.add("Maquina Utilizada");
		   columnNames.add("Aplicadores");
		   columnNames.add("Carencia");
		   columnNames.add("Reingreso");
		   columnNames.add("Fecha Viable Cosecha");
		   columnNames.add("Hora Inicio");
		   columnNames.add("Hora Termino");
		 	
		    Row row = null;
		    Cell cell = null;
		    try {

		        Workbook wb = new HSSFWorkbook();
		        HSSFCellStyle styleHeader = (HSSFCellStyle) wb.createCellStyle();
		        HSSFFont fontHeader = (HSSFFont) wb.createFont();
		        fontHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		        styleHeader.setFont(fontHeader);
		        Sheet sheet = wb.createSheet("Aplicaciones");
		         
		        sheet.setAutoFilter(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, columnNames.size()));
		        sheet.createFreezePane(0, 1);
		        
		        sheet.createRow((short) 0);
		        
		        row = sheet.createRow((short) 0);

		        for (int i = 0; i < columnNames.size(); i++) {
		            cell = row.createCell(i);
		            cell.setCellValue(columnNames.get(i));
		            cell.setCellStyle(styleHeader);
		        }

		        int j = 1;
	        
		        for (Object[] temp : lst) {
		        	
		            row = sheet.createRow((short) j);
		            HSSFCellStyle styleRow = (HSSFCellStyle) wb.createCellStyle();
		            HSSFFont fontRow = (HSSFFont) wb.createFont();
		            fontRow.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		            styleRow.setFont(fontRow);
		            row.createCell(0).setCellValue(temp[0].toString());
		            row.createCell(1).setCellValue(temp[1].toString());
		            row.createCell(2).setCellValue(temp[2].toString());
		            row.createCell(3).setCellValue(temp[3].toString());
		            row.createCell(4).setCellValue(temp[4].toString());
		            row.createCell(5).setCellValue(temp[5].toString());
		            row.createCell(6).setCellValue(temp[6].toString());
		            row.createCell(7).setCellValue(temp[7].toString());
		            row.createCell(8).setCellValue(temp[8].toString());
		            row.createCell(9).setCellValue(temp[9].toString());
		            row.createCell(10).setCellValue(temp[10].toString());
		            row.createCell(11).setCellValue(temp[11].toString());
		            row.createCell(12).setCellValue(temp[12].toString());
		            row.createCell(13).setCellValue(temp[13].toString());
		            row.createCell(14).setCellValue(temp[14].toString());
		            row.createCell(15).setCellValue(temp[15].toString());
		            row.createCell(16).setCellValue(temp[16].toString());
		            row.createCell(17).setCellValue(temp[17].toString());
		            row.createCell(18).setCellValue(temp[18].toString());
		            row.createCell(19).setCellValue(temp[19].toString());
		            row.createCell(20).setCellValue(temp[20].toString());
		            row.createCell(21).setCellValue(temp[21].toString());
		            row.createCell(22).setCellValue(temp[22].toString());
		            row.createCell(23).setCellValue(temp[23].toString());
		            row.createCell(24).setCellValue(temp[24].toString());
		            row.createCell(25).setCellValue(temp[25].toString());
		            row.createCell(26).setCellValue(temp[26].toString());
		            row.createCell(27).setCellValue(temp[27].toString());
		            row.createCell(28).setCellValue(temp[28].toString());
		            row.createCell(29).setCellValue(temp[29].toString());
		            /*
		            row.createCell(30).setCellValue(temp[30].toString());
		            row.createCell(31).setCellValue(temp[31].toString());
		            */

		            j++;
		        }

		        String excelFileName = "datos.xls";

		        /*
		        FileOutputStream fos = new FileOutputStream(excelFileName);
		        wb.write(fos);
		        fos.flush();
		        fos.close();

		        InputStream stream = new BufferedInputStream(new FileInputStream(excelFileName));
		        DefaultStreamedContent exportFile = new DefaultStreamedContent(stream, "application/xls", excelFileName);
                */
		        FacesContext facesContext = FacesContext.getCurrentInstance();
		        ExternalContext externalContext = facesContext.getExternalContext();
		        externalContext.setResponseContentType("application/vnd.ms-excel");
		        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"datos.xls\"");

		        wb.write(externalContext.getResponseOutputStream());
		        facesContext.responseComplete();

		    } catch (Exception e) {
		       logger.info(e.getMessage()+" "+e.getLocalizedMessage());
		    }

}
		
		
		
	
	
	
	public String nuevo(){
			  	  
	
		  formCabController.setCabApp(new CabAplicacion());
		  formCabController.setDetAplicacionAux(new DetAplicacion());
		  formCabController.setTitulo("Nuevo Registro");
	      formCabController.setDetAplicacionDataModel(new DetAplicacionDataModel(new ArrayList<DetAplicacion>()));
		  return "FormCab?faces-redirect=true"; 

	
		
	}
	
	public void eliminar(){
		FacesContext facesContext = FacesContext.getCurrentInstance();

		try{
		  
		  
			
		  //detAplicacionRepository.deleteAll(cabAplicacion.getDetAplicacion());
		  //detAplicacionRepository.flush();
			cabAplicacion.getDetAplicacion().forEach(x->{
				
				//CabAplicacion c = x.getCabAplicacion();
				//c.eliminarDetalle(x);
				//detAplicacionRepository.delete(x);
			});
		       
          cabAplicacion.getDetAplicacion().clear();
		  cabAplicacionRepository.delete(cabAplicacion);
		  List<CabAplicacion> lst = cabAplicacionDataModel.getLstCabAp();
		  lst.remove(cabAplicacion);
		  cabAplicacionDataModel = new CabAplicacionDataModel(lst);
		  
		  facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se elimino Correctamente", null));
		
		}catch(Exception e){
			  facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

		}
		
	}
	
	public String modificar(){
		
		  
		  FacesContext facesContext = FacesContext.getCurrentInstance();
		  Flash flash = facesContext.getExternalContext().getFlash();
		  flash.setKeepMessages(true);
		
		if(cabAplicacion != null){
		  formCabController.setCabApp(cabAplicacion);
		  formCabController.setTitulo("Modificar Registro");
	      formCabController.setDetAplicacionDataModel(new DetAplicacionDataModel(cabAplicacion.getDetAplicacion()));
		  return "FormCab?faces-redirect=true"; 

		}else{
			 
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe Seleccionar una cabecera", null));
    		  return "ListarCab?faces-redirect=true"; 
		}
	}
	
	
	public void obtener(){
		
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 logger.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
		 
		 if( authentication != null){
		  usuario = authentication.getName();
		  User u         = userRepository.findByUsername(usuario);
		  Set<Role> roles = u.getRoles();
		  isAdmin = roles.stream().anyMatch(new Predicate<Role>(){

			@Override
			public boolean test(Role t) {
				// TODO Auto-generated method stub
				return t.getName().equals("ROLE_ADMIN");
			}

		});
		
	    if(isAdmin)
		     cabAplicacionDataModel = new CabAplicacionDataModel((List<CabAplicacion>)cabAplicacionRepository.findAll());
		else
	 		 cabAplicacionDataModel = new CabAplicacionDataModel((List<CabAplicacion>)cabAplicacionRepository.findByUsuario(usuario));

		logger.info(cabAplicacionDataModel.toString());
		  }
		
		
	}
	
	
    public void ver(){
	   
	 
	   
   }
	
	
	
	
	public CabAplicacionDataModel getCabAplicacionDataModel() {
		return cabAplicacionDataModel;
	}


	public void setCabAplicacionDataModel(CabAplicacionDataModel cabAplicacionDataModel) {
		this.cabAplicacionDataModel = cabAplicacionDataModel;
	}


	public CabAplicacion getCabAplicacion() {
		return cabAplicacion;
	}


	public void setCabAplicacion(CabAplicacion cabAplicacion) {
		this.cabAplicacion = cabAplicacion;
	}
	
	
	
	
	
}
