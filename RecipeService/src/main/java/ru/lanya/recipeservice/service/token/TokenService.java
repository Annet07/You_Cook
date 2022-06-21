package ru.lanya.recipeservice.service.token;

import java.util.Optional;

import ru.lanya.magiccooking.dto.TokenDto;
import ru.lanya.magiccooking.entity.User;
import ru.lanya.magiccooking.entity.token.RefreshToken;

public interface TokenService {
    TokenDto getNewTokens(User user);
    Optional<RefreshToken> findByRefreshToken(String token);
    String updateAccessToken(String refreshToken);
    Optional<User> findByAccessToken(String accessToken);
}
