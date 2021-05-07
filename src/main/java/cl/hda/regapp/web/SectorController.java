package cl.hda.regapp.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

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

	


}
