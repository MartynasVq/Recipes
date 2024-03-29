package learn.spring.martynas.services;

import learn.spring.martynas.commands.RecipeCommand;
import learn.spring.martynas.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(Long l);

    void deleteById(Long l);
}
