package cl.hda.regapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.hda.regapp.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
}
