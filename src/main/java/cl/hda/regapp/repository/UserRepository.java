package cl.hda.regapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.hda.regapp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
