package cl.hda.regapp.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

import cl.hda.regapp.entities.CabAplicacion;
import cl.hda.regapp.entities.Sector;
import cl.hda.regapp.entities.TabCod;
import cl.hda.regapp.repository.SectorRepository;

@ManagedBean
@Named("formCab")
public class FormCabController {
  
  @Autowired
  SectorRepository sectorRepository;
  
	private Sector sectorSel = new Sector();

  
  CabAplicacion cabApp      = new CabAplicacion();
  List<Sector> sectores     = new ArrayList<>();
  List<TabCod> lstObjetivos = new ArrayList<>();
  List<TabCod> lstMetodos   = new ArrayList<>();
  List<TabCod> lstMaquinas  = new ArrayList<>();
  List<TabCod> lstUmedida   = new ArrayList<>();
  
  
  
  
  @PostConstruct
  public void init(){
	  
	  sectores = (List<Sector>) sectorRepository.findAll();
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
  

}
