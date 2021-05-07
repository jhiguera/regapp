package cl.hda.regapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.hda.regapp.entities.TabCod;

@Repository
public interface TabCodRepository extends CrudRepository<TabCod,Long> {
	
	List<TabCod> findByCodigo(String codigo);
	
	@Query(nativeQuery = true, value =
	           "SELECT " +
	           "    t.codigo " +
	           "FROM " +
	           "    tab_cod t " +
	           "GROUP BY " +
	           "    t.codigo")
	List<String> obtenerCodigos();

}
