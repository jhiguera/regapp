package cl.hda.regapp.validator;

import java.time.LocalDateTime;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import cl.hda.regapp.entities.Conversion;
import cl.hda.regapp.entities.Producto;
import cl.hda.regapp.repository.ConversionRepository;
import cl.hda.regapp.web.FormCabController;


@FacesValidator("unidadMedidaValidator")
@Component("unidadMedidaValidator")
public class UnidadMedidaValidator implements Validator {
	
	@Autowired
	ConversionRepository conversionRepository;
	
	Producto p;
	
	List<Conversion> lstConversiones;
	
	  private static final Logger logger = LoggerFactory.getLogger(UnidadMedidaValidator.class);

	
	public UnidadMedidaValidator(){
		
	}
	
	@Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		lstConversiones = (List<Conversion>) conversionRepository.findAll();

   	    UIInput productoComponent = (UIInput) component.getAttributes().get("param");
   	    logger.info(lstConversiones.get(0).getExpresion());
		String uMedida = value.toString();
		
		 if(productoComponent != null)
			 p = (Producto)productoComponent.getValue();
		 		
		if(p != null){
			Conversion  conversion_1 = lstConversiones.stream().filter(uMedida1->uMedida.equals(uMedida1.getuMedida1()))
	  	          .filter(uMedida2->p.getuMedida().equals(uMedida2.getuMedida2()))
					  .findAny()
	  	  .orElse(null);
					
			if(conversion_1 == null){
				  throw new ValidatorException(new FacesMessage(
			                 FacesMessage.SEVERITY_ERROR, "Unidad invalida", null));
			}
		}
		
    	
    }

}
