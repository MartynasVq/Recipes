package learn.spring.martynas.controllers;

import learn.spring.martynas.domain.Recipe;
import learn.spring.martynas.exceptions.NotFoundException;
import learn.spring.martynas.repositories.RecipeRepository;
import learn.spring.martynas.services.RecipeService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

class RecipeControllerTest {

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeService recipeService;

    RecipeController recipeController;

    @BeforeEach()
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeController = new RecipeController(recipeService);
    }
    @Test
    public void testGetRecipe() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/show")).andExpect(status().isOk()).andExpect(view().name("recipe/index"));

    }

    @Test
    public void exceptionTesting() throws Exception
    {
        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyLong())).thenThrow(NotFoundException.class);

        //System.out.println(recipeRepository.findById(1L));

        assertThrows(NotFoundException.class, () -> recipeRepository.findById(1L), "Expected findById to throw error");
    }

}