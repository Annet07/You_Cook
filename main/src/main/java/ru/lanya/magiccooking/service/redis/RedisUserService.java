package ru.lanya.magiccooking.service.redis;

import ru.lanya.magiccooking.entity.User;

public interface RedisUserService {
    void addTokenToUser(User user, String accessToken, String refreshToken);

    void addAllTokensToBlacklist(User user);
}
