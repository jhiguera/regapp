package cl.hda.regapp.converter;

import java.io.Serializable;
import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cl.hda.regapp.entities.Role;
import cl.hda.regapp.repository.RoleRepository;

@FacesConverter(forClass = Role.class, value="roleConverter")
@Component("roleConverter")
public class RoleConverter implements Converter,Serializable{

	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		

		if(value != null) {
			Optional<Role> s = roleRepository.findById(new Long(value));
			return s.get();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value!=null) {
			
			try {
			
				Role s = (Role)value;
			    return s.toString();
			}catch(ClassCastException e ) {
				
				Optional<Role> s = roleRepository.findById((long) value);
				return s.get().toString();
			}
			
		}
		
		return "";
	}

}