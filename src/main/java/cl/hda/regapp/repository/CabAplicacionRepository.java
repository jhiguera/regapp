package cl.hda.regapp.repository;

import java.util.Collection;
import java.util.List;

import javax.persistence.NamedNativeQuery;

import org.aspectj.apache.bcel.classfile.Module.Export;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cl.hda.regapp.entities.CabAplicacion;


			  
public interface CabAplicacionRepository extends JpaRepository <CabAplicacion, Long> {

	List<CabAplicacion> findByUsuario(String usuario);
	
	

}
