package cl.hda.regapp.validation;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import cl.hda.regapp.entities.DetAplicacion;
import cl.hda.regapp.web.FormCabController;

public class fechaValidacion implements ConstraintValidator<fechaMayorOrIgual, LocalTime> {
    
	private static final Logger logger = LoggerFactory.getLogger(fechaValidacion.class);

	String horaInicio;
	String horaTermino;
	
	@Override
    public void initialize(fechaMayorOrIgual constraintAnnotation) {
        horaInicio = constraintAnnotation.horaInicio();
        horaTermino = constraintAnnotation.horaTermino();
        logger.info("initialize");
    }
	 
    @Override
    public boolean isValid(LocalTime requestObject, ConstraintValidatorContext context) {
        
    	logger.info("isValid");
    	/*
    	Field horaInicioField = FieldUtils.getField(requestObject.getClass(), horaInicio, true);
        Field horaTerminoField = FieldUtils.getField(requestObject.getClass(), horaTermino, true);
 
        LocalTime horaInicioLocal = (LocalTime) ReflectionUtils.getField(horaInicioField, requestObject);
        LocalTime horaTerminoLocal = (LocalTime) ReflectionUtils.getField(horaTerminoField, requestObject);
 
      		
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                "mensaje")
                .addPropertyNode(horaInicio)
                .addPropertyNode(horaTermino)
                .addConstraintViolation();
                */
        
        return  requestObject.isAfter(LocalTime.now());
    	
    	
      	
    	
    	
    }

	
}