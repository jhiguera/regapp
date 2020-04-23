package cl.hda.regapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.hda.regapp.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
