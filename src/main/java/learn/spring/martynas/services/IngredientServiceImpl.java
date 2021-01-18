package learn.spring.martynas.services;

import learn.spring.martynas.commands.IngredientCommand;
import learn.spring.martynas.converters.IngredientCommandToIngredient;
import learn.spring.martynas.converters.IngredientToIngredientCommand;
import learn.spring.martynas.domain.Ingredient;
import learn.spring.martynas.domain.Recipe;
import learn.spring.martynas.repositories.RecipeRepository;
import learn.spring.martynas.repositories.UOMRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UOMRepository uomRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, RecipeRepository recipeRepository, UOMRepository uomRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.uomRepository = uomRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndId(Long recipeId, Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent())
            log.error("Recipe not found.");

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommand = recipe.getIngredientSet().stream().
                filter(i -> i.getId().equals(id)).map(i -> ingredientToIngredientCommand.convert(i)).findFirst();

        if(!ingredientCommand.isPresent())
            log.error("Ingredient not found.");

        return ingredientCommand.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());

        if(!recipeOptional.isPresent()){

            //todo toss error if not found!
            log.error("Recipe not found for id: " + ingredientCommand.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredientSet()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(ingredientCommand.getDescription());
                ingredientFound.setAmount(ingredientCommand.getAmount());
                ingredientFound.setUom(uomRepository.findById(ingredientCommand.getUnitOfMeasure().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            } else {
                //add new Ingredient
                Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
                ingredient.setRecipe(recipe);

                recipe.getIngredientSet().add(ingredientCommandToIngredient.convert(ingredientCommand));


            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredientSet().stream().filter(r ->
                    r.getId().equals(ingredientCommand.getId())).findFirst();

            if(!savedIngredientOptional.isPresent()) {

                savedIngredientOptional = savedRecipe.getIngredientSet().stream().filter(r ->
                        r.getDescription().equals(ingredientCommand.getDescription())).filter(r ->
                        r.getAmount().equals(ingredientCommand.getAmount())).filter(r -> r.getUom().getId().equals(
                        ingredientCommand.getUnitOfMeasure().getId())).findFirst();
            }

            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
        }



    }
}
