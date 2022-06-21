package ru.lanya.recipeservice.service;



import java.util.Optional;

import ru.lanya.magiccooking.entity.User;

public interface UserService {
    Optional<User> findById(Long userId);
    Optional<User> findByAccessToken(String accessToken);
}
