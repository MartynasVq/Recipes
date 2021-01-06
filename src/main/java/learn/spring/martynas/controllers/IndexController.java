package learn.spring.martynas.controllers;

import learn.spring.martynas.domain.Category;
import learn.spring.martynas.domain.UnitOfMeasure;
import learn.spring.martynas.repositories.CategoryRepository;
import learn.spring.martynas.repositories.UOMRepository;
import learn.spring.martynas.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {

        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
