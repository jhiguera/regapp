package cl.hda.regapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.hda.regapp.entities.Conversion;

@Repository
public interface ConversionRepository extends CrudRepository<Conversion,Long> {

}
