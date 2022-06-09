package ru.lanya.magiccooking.service;

import ru.lanya.magiccooking.entity.Recipe;
import ru.lanya.magiccooking.entity.Response;
import ru.lanya.magiccooking.entity.User;
import ru.lanya.magiccooking.form.UserForm;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Response signUp(User user);
    Response signIn(UserForm userForm);
    void blockAllTokens(Long userId);
    Optional<User> findByAccessToken(String accessToken);
    User findById(Long userId);
    boolean subscribe(User from, Long to);
}
