package cl.hda.regapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.hda.regapp.entities.Producto;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
