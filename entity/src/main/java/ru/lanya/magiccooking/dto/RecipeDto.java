package ru.lanya.magiccooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lanya.magiccooking.entity.Ingredient;
import ru.lanya.magiccooking.entity.Step;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecipeDto {

    private String name;
    private List<Ingredient> ingredients;
    private List<Step> steps;
}
