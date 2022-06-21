package ru.lanya.recipeservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lanya.magiccooking.entity.User;
import ru.lanya.recipeservice.repository.UserRepository;
import ru.lanya.recipeservice.service.token.TokenService;

@Service
public class  UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> findByAccessToken(String accessToken) {
        return tokenService.findByAccessToken(accessToken);
    }
}
