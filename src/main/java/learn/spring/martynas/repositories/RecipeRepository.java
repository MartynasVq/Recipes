package learn.spring.martynas.repositories;

import learn.spring.martynas.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
