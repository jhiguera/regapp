package cl.hda.regapp.converter;

import java.io.Serializable;
import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cl.hda.regapp.entities.Producto;
import cl.hda.regapp.repository.ProductoRepository;

@FacesConverter(forClass = Producto.class, value="productoConverter")
@Component("productoConverter")
public class ProductoConverter implements Converter,Serializable{

	
	
	
	
	@Autowired
	ProductoRepository productoRepository;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Optional<Producto> s = productoRepository.findById(new Long(value));
		return s.get();

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null) {
			Producto p = (Producto)value;
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
