package cl.hda.regapp.web;

import java.io.IOException;
import java.util.Collection;
import java.util.function.Predicate;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

import cl.hda.regapp.entities.User;
import cl.hda.regapp.repository.UserRepository;

@ManagedBean
@SessionScope
public class HomeController {

	String nombre;
	String usuario;
	
	@Autowired
	UserRepository userRepository;
   
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private Boolean isAdmin;
	
	@PostConstruct
	public void init() {
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	Collection col = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    	isAdmin = col.stream().anyMatch(c->c.toString().equals("ROLE_ADMIN"));
		
    	
    	
    	usuario = authentication.getName();
		User u = userRepository.findByUsername(usuario);
		nombre = u.getName();
		
	}
	
	  public void onIdle() {
	       
		  logger.info("on idle");
	    }
	 
	    public void onActive() {
	      
	      logger.info("on Active ");
	    }
	
	
	
	
	public void salir() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("/logout");
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
    
	
	
}
