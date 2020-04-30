package cl.hda.regapp.service;

import cl.hda.regapp.entities.User;

public interface UserService {
    
	void save(User user);
    User findByUsername(String username);
}
