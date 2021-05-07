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

import cl.hda.regapp.entities.Producto;
import cl.hda.regapp.entities.TabCod;
import cl.hda.regapp.model.TabCodDataModel;
import cl.hda.regapp.repository.TabCodRepository;

@Named("tabcod")
@SessionScope
public class TabCodController {
	
    private static final Logger logger = LoggerFactory.getLogger(TabCodController.class);

    @Inject
    FormCabController formCabController;
        
	@Autowired
	TabCodRepository tabCodRepository;
	
	List<String> codigos = new ArrayList<>();
	String codigo;
	TabCodDataModel tabCodDataModel = new TabCodDataModel();
	
	@PostConstruct
	public void init(){
		
		codigos = tabCodRepository.obtenerCodigos();
	}
	
       public void salvar() {
		
		for(TabCod t : tabCodDataModel.getLstTabCods()) {
			
			if( t.getId() == -1)
				t.setId(null);
			tabCodRepository.save(t);
		}
		
		formCabController.cargarParametros();
		
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Se grabo Correctamente"));

     }
	
	
	public void obtener(){
		
		tabCodDataModel = new TabCodDataModel(tabCodRepository.findByCodigo(codigo)) ;
		logger.info(codigo);
	}
	
	public void addRow(){
		
		if(codigo != null){
		
			TabCod t = new TabCod();
			t.setId(-1L);
			t.setCodigo(codigo);
			tabCodDataModel.getLstTabCods().add(t);
		}else
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar codigo antes"));

		
	}
	
	public void onRowCancel(RowEditEvent event){
		
		TabCod t = (TabCod)event.getObject();
		if( t.getId() == -1)
		  tabCodDataModel.getLstTabCods().remove(t);
		
	}
	

	public List<String> getCodigos() {
		return codigos;
	}

	public void setCodigos(List<String> codigos) {
		this.codigos = codigos;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public TabCodDataModel getTabCodDataModel() {
		return tabCodDataModel;
	}

	public void setTabCodDataModel(TabCodDataModel tabCodDataModel) {
		this.tabCodDataModel = tabCodDataModel;
	}
	
	
	
}
