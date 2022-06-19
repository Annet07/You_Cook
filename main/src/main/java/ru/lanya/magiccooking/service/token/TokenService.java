package ru.lanya.magiccooking.service.token;

import ru.lanya.magiccooking.dto.TokenDto;
import ru.lanya.magiccooking.entity.User;
import ru.lanya.magiccooking.entity.token.RefreshToken;

import java.util.Optional;

public interface TokenService {
    TokenDto getNewTokens(User user);
    Optional<RefreshToken> findByRefreshToken(String token);
    String updateAccessToken(String refreshToken);
    Optional<User> findByAccessToken(String accessToken);
}
