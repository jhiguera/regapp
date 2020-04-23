package cl.hda.regapp.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import cl.hda.regapp.entities.Role;
import cl.hda.regapp.entities.User;
import cl.hda.regapp.repository.RoleRepository;
import cl.hda.regapp.repository.UserRepository;

@ManagedBean
@Named("user")
public class UserControlller {

	@Autowired
    public UserRepository userManager;
	
	@Autowired
	public RoleRepository roleRepository;
	
	
    private User model = new User();
    private List<Role> lstRole = new ArrayList<>();
    
 
    @PostConstruct
    public void init() {
    	
    	lstRole = roleRepository.findAll();
    	
    }
    

    public List<Role> getLstRole() {
		return lstRole;
	}

	public void setLstRole(List<Role> lstRole) {
		this.lstRole = lstRole;
	}

	@Inject
    private BCryptPasswordEncoder  passwordEncoder;
 
    public User getModel() {
        return model;
    }
 
    public void setModel(User model) {
        this.model = model;
    }
 
    public String register() {
        try {
            if (!model.getPassword().equals(model.getPasswordConfirm())) {
                throw new Exception("Passwords do not match!!");
            }
            model.setPassword(passwordEncoder.encode(model.getPassword()));
            userManager.save(model);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Registration Failure, " + e.getMessage(), ""));
            return null;
        }
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Registration Success!", ""));
        model = null;
        return "/login.xhtml";
    }
}
