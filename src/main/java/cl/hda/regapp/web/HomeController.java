package cl.hda.regapp.web;

import java.io.IOException;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
	
	
	@PostConstruct
	public void init() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		usuario = authentication.getName();
		User u = userRepository.findByUsername(usuario);
		
		nombre = u.getName();
		
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
    
	
	
}
