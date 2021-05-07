package cl.hda.regapp.converter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import org.springframework.stereotype.Component;

import cl.hda.regapp.entities.Producto;


@FacesConverter(forClass = Producto.class, value="localDateTimeConverter")
@Component("localDateTimeConverter")
public class LocalDateTimeConverter implements javax.faces.convert.Converter {
 
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
    	
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
   	
          return LocalDateTime.parse(value,formatter);

    }
 
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
 
        LocalDateTime dateValue = (LocalDateTime) value;
         
        return dateValue.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
     
}