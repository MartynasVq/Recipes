package learn.spring.martynas.services;

import learn.spring.martynas.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndId(Long recipeId, Long id);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    void deleteById(Long recipeId, Long id);
    }

