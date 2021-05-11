package cl.hda.regapp.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.context.annotation.SessionScope;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import cl.hda.regapp.entities.Producto;
import cl.hda.regapp.entities.Sector;
import cl.hda.regapp.entities.TabCod;
import cl.hda.regapp.model.ProductoDataModel;
import cl.hda.regapp.repository.ProductoRepository;
import cl.hda.regapp.repository.SectorRepository;
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
	SectorRepository sectorRepo;
	
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

	 
	 public void subirArchivoProducto(FileUploadEvent eventosProd) {
	    	
		  
		 
	    	FacesMessage msg = new FacesMessage("Archivo subido correctamente ", eventosProd.getFile().getFileName() + " subido exitosamente.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        // Do what you want with the file
	        try {
	        	
	            copiarArchivo("producto.csv", eventosProd.getFile().getInputstream());
	          
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public void copiarArchivo(String nombreArchivo, InputStream in) {
	    	
	    	try {

	            // write the inputStream to a FileOutputStream
	            OutputStream out = new FileOutputStream(new File("c:\\tmp\\" + nombreArchivo));

	            int read = 0;
	            byte[] bytes = new byte[1024];

	            while ((read = in.read(bytes)) != -1) {
	                out.write(bytes, 0, read);
	            }

	            in.close();
	            out.flush();
	            out.close();

	            System.out.println("Archivo Creado Producto");
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	      
	    public void importarProductos() throws CsvValidationException, IOException {
	    	
	    	
	    	
	    	CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
	    	   	
	    	CSVReader  reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream("c:\\tmp\\"+"producto.csv"), "ISO-8859-1"))
	    			.withCSVParser(parser)
	                .build();
	    	
	    	String[] linea;
			String[] firstLine = reader.readNext();
	    	
	    	List<Producto> lstProductos  = new ArrayList<>();
	    	
	    	/*
	    	 * 0 -> producto
	    	 * 1 -> caracteristica
	    	 * 2 -> ingrediente activo
	    	 * 3 -> reingreso
	    	 * 4 -> umedida
	    	 * 5 -> color etiqueta
	    	 * 6 -> irac
	    	 * 7 -> mda
	    	 * 8 -> temporada
		    	 */
	    	
	    	while ((linea = reader.readNext()) != null) {
	    		
	    		Producto p  = new Producto();
	    		p.setProducto(linea[0]);
	    		p.setCaracteristicas(linea[1]);
	    		p.setIngredienteActivo(linea[2]);
	    		p.setReingreso(Integer.parseInt(linea[3]));
	    		p.setuMedida(linea[4]);
	    		p.setColorEtiqueta(linea[5]);
	    		p.setMdaIrac(linea[6]);
	    		p.setMdaFrac(linea[7]);
	    		p.setTemporada(Integer.parseInt(linea[8]));
	    		lstProductos.add(p);
	    		
	    	}
	        
	      	reader.close();
	  	
	    	try {
	    	   List<Producto> lst = 	(List<Producto>) productoRepository.saveAll(lstProductos);
	    	   List<Producto> listaProductos =   productoDataModel.getLstProducto();
	    	   listaProductos.addAll(lst);
	    	   
	    	   productoDataModel  = new ProductoDataModel(listaProductos);
	    	   
	    	   
	    	}catch(DataIntegrityViolationException  e) {
	    	    		
	    	}catch(Exception e) {}
	    	
	    	
	    	/** leer productos de la bd **/
	    	
	    	
	    	
	       	FacesMessage msg = new FacesMessage("Importacion Productos", "Se importaron los Productos correcatamene");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        
	        
	        

	       	
	   }
	    
	  
	  

	  public String getFileContent() {
	      return fileContent;
	  }

	  public String getFileName() {
	      return fileName;
	  }
	
	
	

}