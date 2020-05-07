package cl.hda.regapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.hda.regapp.entities.TabCod;

@Repository
public interface TabCodRepository extends CrudRepository<TabCod,Long> {
	
	List<TabCod> findByCodigo(String codigo);

}
