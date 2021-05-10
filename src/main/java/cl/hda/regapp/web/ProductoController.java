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

	 
	  

	 private String destination = "c:\\tmp\\";
	  public void handleUpload(FileUploadEvent event) {
	        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        // Do what you want with the file
	        try {
	            copyFile("producto.csv", event.getFile().getInputstream());
	          
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        try {
				leerFile();
			} catch (CsvValidationException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CsvException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }

	  
	    public void leerFile() throws IOException, CsvException {
	    	
	    	
	    	CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
	    	

	    	
	    	CSVReader  reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(destination+"producto.csv"), "ISO-8859-1"))
	    			.withCSVParser(parser)
                    .build();

	    	
	    	String[] linea;
			String[] firstLine = reader.readNext();
	    	
	    	List<Sector> lstSector  = new ArrayList<>();
	    	
	    	/*
	    	 * 0 -> sector
	    	 * 1 -> parcela
	    	 * 2 -> pseudonimo
	    	 * 3 -> superficie
	    	 * 4 -> especie
	    	 * 5 -> variedad copa
	    	 * 6 -> variedad portainjerto
	    	 * 7 -> plantas reales
	    	 * 8 -> temporada
 	    	 */
	    	
	    	while ((linea = reader.readNext()) != null) {
	    		
	    		Float superficie;
				if(  linea[3].length() > 0  && !linea[3].equals("-")) {
					
		    	      System.out.println("length linea "+linea[3].length());
    	    	      superficie = Float.parseFloat(linea[3].replace(",", "."));
				}
	    		else
	    			superficie =null;
	    		
				
				Double plantasReales ;
				if(linea[7] != null && !linea[7].equals("-") && linea[7].length()>0 )
				    plantasReales = Double.parseDouble(linea[7].replace(",",".") );
				else
					plantasReales = null;
					
				
				
	    		
	    		Sector s = new Sector();
	    		s.setSector(linea[0]);
	    		s.setParcela(linea[1]);
	    		s.setPseudonimo(linea[2]);
	    		s.setSuperficieReal(superficie);
	    		s.setEspecie(linea[4]);
	    		s.setVariedad(linea[5]);
	    		s.setPortainjerto(linea[6]);
	    		s.setPlantasReales(plantasReales);
	    		s.setTemporada(Integer.parseInt(linea[8]));
	    		
	    		lstSector.add(s);
	    		
	    	}
	        
	    	
	    	sectorRepo.saveAll(lstSector.subList(0, 1));
	    	
	    	reader.close();
	    		    	
	    }
	  
	  
	    public void copyFile(String fileName, InputStream in) {
	        try {

	            // write the inputStream to a FileOutputStream
	            OutputStream out = new FileOutputStream(new File(destination + fileName));

	            int read = 0;
	            byte[] bytes = new byte[1024];

	            while ((read = in.read(bytes)) != -1) {
	                out.write(bytes, 0, read);
	            }

	            in.close();
	            out.flush();
	            out.close();

	            System.out.println("Archivo Creado");
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	  
	  
	  

	  public String getFileContent() {
	      return fileContent;
	  }

	  public String getFileName() {
	      return fileName;
	  }
	
	
	

}