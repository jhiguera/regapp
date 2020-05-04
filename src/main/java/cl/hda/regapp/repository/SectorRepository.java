package cl.hda.regapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.hda.regapp.entities.Sector;

@Repository
public interface SectorRepository extends CrudRepository<Sector, Long> {

}
