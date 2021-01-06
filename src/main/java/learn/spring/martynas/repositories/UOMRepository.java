package learn.spring.martynas.repositories;

import learn.spring.martynas.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

public interface UOMRepository extends CrudRepository<UnitOfMeasure, Long> {

}
