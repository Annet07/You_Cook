package ru.lanya.magiccooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lanya.magiccooking.entity.Recipe;
import ru.lanya.magiccooking.entity.User;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDtoForOther {

    private String username;
    private Long subscribers;
    private List<Recipe> recipes;

}
