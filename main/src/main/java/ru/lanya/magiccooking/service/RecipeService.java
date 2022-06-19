package ru.lanya.magiccooking.service;

import ru.lanya.magiccooking.dto.RecipeDto;
import ru.lanya.magiccooking.entity.Recipe;
import ru.lanya.magiccooking.entity.Response;
import ru.lanya.magiccooking.entity.User;

import java.util.List;

public interface RecipeService {
    List<Recipe> findAllByAuthor(Long authorId);
    Recipe findById(Long recipeId);
    RecipeDto createRecipe(Long userId, RecipeDto recipeDto);
    String deleteRecipe(Long recipeId);
    String editRecipe(RecipeDto recipeDto, Long recipeId);
    List<Recipe> getFavorites(User user);
}
