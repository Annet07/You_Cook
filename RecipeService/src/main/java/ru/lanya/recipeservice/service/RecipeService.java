package ru.lanya.recipeservice.service;



import java.util.List;

import ru.lanya.magiccooking.dto.RecipeDto;
import ru.lanya.magiccooking.entity.Recipe;
import ru.lanya.magiccooking.entity.User;

public interface RecipeService {
    List<Recipe> findAllByAuthor(Long authorId);
    Recipe findById(Long recipeId);
    RecipeDto createRecipe(Long userId, RecipeDto recipeDto);
    String deleteRecipe(Long recipeId);
    String editRecipe(RecipeDto recipeDto, Long recipeId);
    List<Recipe> getFavorites(User user);
}
