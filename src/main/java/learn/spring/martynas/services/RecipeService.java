package learn.spring.martynas.services;

import learn.spring.martynas.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
