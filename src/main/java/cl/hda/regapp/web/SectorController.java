package cl.hda.regapp.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
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

import cl.hda.regapp.entities.Sector;
import cl.hda.regapp.entities.TabCod;
import cl.hda.regapp.model.ProductoDataModel;
import cl.hda.regapp.model.SectorDataModel;
import cl.hda.regapp.repository.SectorRepository;
import cl.hda.regapp.repository.TabCodRepository;


@Named("sec")
@SessionScope
public class SectorController {
	

	@Inject
	FormCabController formCabController;
	
	SectorDataModel sectorDataModel;
	
	@Autowired
	SectorRepository sectorRepository;
	
	 @Autowired
	TabCodRepository tabCodRepository;
	  
	  List<TabCod> lstUmedidaGasto = new ArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(SectorController.class);

	
	@PostConstruct
	public void init(){
	   
		sectorDataModel = new SectorDataModel((List<Sector>) sectorRepository.findAll());
    	lstUmedidaGasto = tabCodRepository.findByCodigo("unidad_medida_gasto");

		
		
	}

	public void onRowCancel(RowEditEvent event){
		
		Sector s = (Sector) event.getObject();
		if(s.getId() == -1)
		    sectorDataModel.getLstSectores().remove(sectorDataModel.getRowIndex());
		
		
	}
	
	public void salvar() {
		
		for(Sector c : sectorDataModel.getLstSectores()) {
			
			if( c.getId() == -1)
				c.setId(null);
			c.setCabAplicacion(null);
			sectorRepository.save(c);
		}
		
		formCabController.cargarSectores();
		
		
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Se grabo Correctamente"));

     }
	
	public void addRow(){
		
		Sector s = new Sector();
		s.setId(-1L);
		s.setCuartelero("S/C");
		s.setEspecie("TOMATE");
		s.setParcela("MANDROGNE");
		s.setPortainjerto("NO");
		s.setSuperficieReal(30f);
		s.setSector("1.40");
		s.setVariedad("NO");
		//s.setPlantasReales(3000);
		sectorDataModel.getLstSectores().add(s);
		
	}

	public SectorDataModel getSectorDataModel() {
		return sectorDataModel;
	}

	public void setSectorDataModel(SectorDataModel sectorDataModel) {
		this.sectorDataModel = sectorDataModel;
	}

	
    public void subirArchivo(FileUploadEvent event) {
    	
    	FacesMessage msg = new FacesMessage("Archivo subido correctamente ", event.getFile().getFileName() + " subido exitosamente.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file
        try {
            copiarArchivo("sector.csv", event.getFile().getInputstream());
          
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

            System.out.println("Archivo Creado");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
      
    public void importarSectores() throws CsvValidationException, IOException {
    	
    	System.out.println("aquiii");
    	
    	
    	CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
    	   	
    	CSVReader  reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream("c:\\tmp\\"+"sector.csv"), "ISO-8859-1"))
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
        
      	reader.close();
  	
    	try {
    	   	sectorRepository.saveAll(lstSector);
    	}catch(DataIntegrityViolationException  e) {
    	    		
    	}catch(Exception e) {}
    	
       	FacesMessage msg = new FacesMessage("Importacion sectores", "Se importaron los sectores correcatamene");
        FacesContext.getCurrentInstance().addMessage(null, msg);

       	
   }
    

}
