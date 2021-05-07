package cl.hda.regapp.web;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

import com.opencsv.CSVReader;

import cl.hda.regapp.entities.Producto;
import cl.hda.regapp.entities.TabCod;
import cl.hda.regapp.model.ProductoDataModel;
import cl.hda.regapp.repository.ProductoRepository;
import cl.hda.regapp.repository.TabCodRepository;

@Named("prod")
@SessionScope
public class ProductoController {
	
	
	@Inject
	FormCabController formCabController;
	
	ProductoDataModel productoDataModel;
	
    private UploadedFile file;

	
	
	
	@Autowired
	ProductoRepository productoRepository;
	
	 @Autowired
	TabCodRepository tabCodRepository;
	  
	List<TabCod> lstUmedidaGasto = new ArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

	
	@PostConstruct
	public void init(){
	   
		productoDataModel = new ProductoDataModel((List<Producto>) productoRepository.findAll());
    	lstUmedidaGasto = tabCodRepository.findByCodigo("unidad_medida_gasto");

		
		
	}
	
	   public void upload() {
	       
		   
		   if (file != null) {
			   System.out.println("acato");

	            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
	            FacesContext.getCurrentInstance().addMessage(null, message);
	        }else
				   System.out.println("nulo");

	    }

	
	
	

	public void onRowCancel(RowEditEvent event){
		
		Producto p = (Producto) event.getObject();
		logger.info(p.toString());
		if(p.getId() == -1)
		    productoDataModel.getLstProducto().remove(productoDataModel.getRowIndex());
		
		
	}
	
	public void salvar() {
		
		for(Producto c : productoDataModel.getLstProducto()) {
			
			if( c.getId() == -1)
				c.setId(null);
			productoRepository.save(c);
		}
		
		formCabController.cargarProductos();
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Se grabo Correctamente"));

     }
	
	public void addRow(){
		
		Producto p = new Producto();
		p.setId(-1L);
		p.setVigente(true);
		productoDataModel.getLstProducto().add(p);
		
	}

	public ProductoDataModel getProductoDataModel() {
		return productoDataModel;
	}


	public void setProductoDataModel(ProductoDataModel productoDataModel) {
		this.productoDataModel = productoDataModel;
	}

	public List<TabCod> getLstUmedidaGasto() {
		return lstUmedidaGasto;
	}

	public void setLstUmedidaGasto(List<TabCod> lstUmedidaGasto) {
		this.lstUmedidaGasto = lstUmedidaGasto;
	}


	public UploadedFile getFile() {
		return file;
	}


	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	
	
	private String fileContent;
	  private String fileName;

	  public void handleUpload(FileUploadEvent event) {
		  
		  
	      UploadedFile file = event.getFile();

	      System.out.println("nombre "+new String(file.getContents()));

		  
	      
	  }

	  public String getFileContent() {
	      return fileContent;
	  }

	  public String getFileName() {
	      return fileName;
	  }
	

}
