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
public class UserDtoForMyself {

    private String username;
    private String email;
    private String telegram;
    private Long subscribersCount;
    private List<User> subscribers;
    private List<User> subscriptions;
    private List<Recipe> recipes;
}
