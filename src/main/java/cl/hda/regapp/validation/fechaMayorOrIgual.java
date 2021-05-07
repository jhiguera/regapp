package cl.hda.regapp.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.Element;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {fechaValidacion.class})
@Documented
public @interface fechaMayorOrIgual {
	

	String message() default "hora termino no puede ser menor a la de inicio";
	Class[] groups() default {};
	 
    Class[] payload() default {};
    
    String horaInicio();
    String horaTermino();
    
    
    

}
