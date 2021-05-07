package cl.hda.regapp.validator;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.stereotype.Component;


@FacesValidator("dateRangeValidator")
@Component("dateRangeValidator")
public class DateRangeValidator implements Validator {

	 public DateRangeValidator() {
		// TODO Auto-generated constructor stub
	}
		
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    	 
    	 UIInput startDateComponent = (UIInput) component.getAttributes().get("inicio");

         if (!startDateComponent.isValid()) {
             return; // Already invalidated. Don't care about it then.
         }

         LocalDateTime startDate = (LocalDateTime) startDateComponent.getValue();

         if (startDate == null) {
             return; // Let required="true" handle.
         }

         LocalDateTime endDate = (LocalDateTime) value;

         if (startDate.isAfter(endDate)) {
             startDateComponent.setValid(false);
             throw new ValidatorException(new FacesMessage(
                 FacesMessage.SEVERITY_ERROR, "hora de inicio no puede ser mayor a la de termino", null));
         }
  	
    	
    }

}
