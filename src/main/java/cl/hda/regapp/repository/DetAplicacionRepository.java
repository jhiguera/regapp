package cl.hda.regapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import cl.hda.regapp.entities.CabAplicacion;
import cl.hda.regapp.entities.DetAplicacion;

public interface DetAplicacionRepository extends JpaRepository <DetAplicacion,Long> {
	
	
	@Transactional
	long deleteByCabAplicacion(CabAplicacion cabAplicacion);
}
