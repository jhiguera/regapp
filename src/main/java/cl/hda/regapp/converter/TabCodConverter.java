package cl.hda.regapp.converter;

import java.io.Serializable;
import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cl.hda.regapp.entities.TabCod;
import cl.hda.regapp.repository.TabCodRepository;

@FacesConverter(forClass = TabCod.class, value="tabCodConvert")
@Component("tabCodConverter")
public class TabCodConverter implements Converter,Serializable{
	
	@Autowired
	TabCodRepository tabCodRepository;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Optional<TabCod> s = tabCodRepository.findById(new Long(value));
		return s.get();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null) {
			TabCod p = (TabCod)value;
			return p.toString();
			
		}else {
			try {
			   return value.toString();
			}catch(Exception e) {
				return "";
			}
		}
		
	}

}
