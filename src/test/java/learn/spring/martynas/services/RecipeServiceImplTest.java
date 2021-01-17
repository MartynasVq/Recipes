package learn.spring.martynas.services;

import learn.spring.martynas.converters.RecipeCommandToRecipe;
import learn.spring.martynas.converters.RecipeToRecipeCommand;
import learn.spring.martynas.domain.Recipe;
import learn.spring.martynas.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    void getRecipes() {

        Recipe recipe = new Recipe();
        HashSet<Recipe> recipesSet = new HashSet<>();
        recipesSet.add(recipe);
        //

        when(recipeRepository.findAll()).thenReturn(recipesSet);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);

        verify(recipeRepository, times(1)).findAll();
    }
}