package cl.hda.regapp.converter;

import java.io.Serializable;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import cl.hda.regapp.entities.Sector;
import cl.hda.regapp.repository.SectorRepository;


@FacesConverter(forClass = Sector.class, value="sectorConverter")
@Component("sectorConverter")
public class SectorConverter implements Converter,Serializable{

	
	
	
	
	@Autowired
	SectorRepository sectorRepository;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Optional<Sector> s = sectorRepository.findById(new Long(value));
		System.out.println(s.get().getParcela());
		return s.get();

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null) {
			Sector s = (Sector)value;
			return s.toString();
			
		}else {
			try {
			   return value.toString();
			}catch(Exception e) {
				return "";
			}
		}
		
	}

}
