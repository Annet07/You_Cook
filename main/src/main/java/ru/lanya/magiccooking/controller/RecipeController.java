package ru.lanya.magiccooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.lanya.magiccooking.dto.RecipeDto;
import ru.lanya.magiccooking.dto.UserDtoForMyself;
import ru.lanya.magiccooking.dto.UserDtoForOther;
import ru.lanya.magiccooking.entity.Recipe;
import ru.lanya.magiccooking.entity.Response;
import ru.lanya.magiccooking.entity.User;
import ru.lanya.magiccooking.service.RecipeService;
import ru.lanya.magiccooking.service.UserService;

import java.util.List;
import java.util.Objects;

@RestController
@PreAuthorize("isAuthenticated()")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @GetMapping("/profile/{user-id}/recipes")
    public Response getAllRecipes(@PathVariable("user-id") Long userId){
        return Response.builder()
                .success(true)
                .response(recipeService.findAllByAuthor(userId))
                .build();
    }

    @GetMapping("/profile/{user-id}/recipes/{recipe-id}")
    public Response getRecipe(@PathVariable("recipe-id") Long recipeId){
        return Response.builder()
                .success(true)
                .response(recipeService.findById(recipeId))
                .build();
    }

    @PostMapping("/profile/{user-id}/recipes/create")
    public Response createRecipe(@PathVariable("user-id") Long userId,
                                 RecipeDto recipeDto){
        return Response.builder()
                .success(true)
                .response(recipeService.createRecipe(userId, recipeDto))
                .build();
    }

    @PostMapping("/profile/{user-id}/recipes/{recipe-id}/delete")
    public Response deleteRecipe(@PathVariable("user-id") Long userId,
                                 @PathVariable("recipe-id") Long recipeId,
                                 @RequestHeader("A-TOKEN") String accessToken){
        User user = userService.findByAccessToken(accessToken).get();
        if (Objects.equals(user.getId(), userId)){
            return Response.builder()
                    .success(true)
                    .response("Рецепт " + recipeService.deleteRecipe(recipeId) + " успешно удален!")
                    .build();
        }
        return Response.builder()
                .success(false)
                .response("Вы не можете удалить этот рецепт, потому что он создан не Вами!")
                .build();
    }

    @PostMapping("/profile/{user-id}/recipes/{recipe-id}/edit")
    public Response editRecipe(@PathVariable("user-id") Long userId,
                               @PathVariable("recipe-id") Long recipeId,
                               @RequestHeader("A-TOKEN") String accessToken,
                               RecipeDto recipeDto){
        User user = userService.findByAccessToken(accessToken).get();
        if (Objects.equals(user.getId(), userId)){
            return Response.builder()
                    .success(true)
                    .response("Рецепт " + recipeService.editRecipe(recipeDto, recipeId) + " успешно изменен!")
                    .build();
        }
        return Response.builder()
                .success(false)
                .response("Вы не можете изменять этот рецепт, потому что он создан не Вами!")
                .build();
    }

    @GetMapping("/favorites")
    public Response getMyFavorites(@RequestHeader("A-TOKEN") String accessToken){
        List<Recipe> favorites = recipeService.getFavorites(userService.findByAccessToken(accessToken).get());
        if (favorites.isEmpty()){
            return Response.builder()
                    .success(true)
                    .response("Список ваших избранных рецептов пуст!")
                    .build();
        }
        return Response.builder()
                .success(true)
                .response(favorites)
                .build();
    }
}
