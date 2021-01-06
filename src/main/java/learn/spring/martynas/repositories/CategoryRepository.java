package learn.spring.martynas.repositories;

import learn.spring.martynas.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
