package cl.hda.regapp;

import javax.faces.validator.Validator;
import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.sun.faces.config.ConfigureListener;

import cl.hda.regapp.validator.DateRangeValidator;


@SpringBootApplication
public class RegappApplication  {  

	public static void main(String[] args) {
		SpringApplication.run(RegappApplication.class, args);
	}
		
	@Bean
    public ServletRegistrationBean facesServletRegistration() {
        
		ServletRegistrationBean registration = new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
        registration.setLoadOnStartup(1);
        return registration;
    }

	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("classpath:messages");
	    messageSource.setCacheSeconds(10); //reload messages every 10 seconds
	    return messageSource;
	}
	
	@Bean
	public FilterRegistrationBean FileUploadFilter() {
	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(new org.primefaces.webapp.filter.FileUploadFilter());
	    registration.setName("PrimeFaces FileUpload Filter");
	    return registration;
	}
	
    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
	        
        	servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
    		//servletContext.setInitParameter("javax.faces.FACELETS_LIBRARIES", "/WEB-INF/springsecurity.taglib.xml");
        	servletContext.setInitParameter("javax.faces.FACELETS_LIBRARIES", "springsecurity.taglib.xml");
        	servletContext.setInitParameter("primefaces.THEME", "start");
            servletContext.setInitParameter("com.sun.faces.numberOfViewsInSession", "5");
			servletContext.setInitParameter("com.sun.faces.serializeServerState", "false");
			servletContext.setInitParameter("javax.faces.STATE_SAVING_METHOD", "client");
			servletContext.setInitParameter("com.sun.faces.expressionFactory", "org.apache.el.ExpressionFactoryImpl");
			servletContext.setInitParameter("encoding", "UTF-8");
	        servletContext.setInitParameter("forceEncoding", "true");
	        servletContext.setInitParameter("primefaces.UPLOADER","commons");
        
        };
    }
    
    /*
    @Override
	public void onStartup(ServletContext servletContext) throws ServletException {
    	System.out.println("aquiiiii");
		servletContext.setInitParameter("javax.faces.FACELETS_LIBRARIES", "/WEB-INF/springsecurity.taglib.xml");
		super.onStartup(servletContext);
    	
    }
    
    
    @Bean
    public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
        return new ServletListenerRegistrationBean<>(new ConfigureListener());
    }

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return null;
	}
	
    */

}
