package ru.lanya.magiccooking.entity.redis;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("redis_user")
public class RedisUser {
    @Id
    private String id;
    private List<String> accessTokens;
    private List<String> refreshTokens;
    private Long userId;
}
