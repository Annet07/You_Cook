package ru.lanya.magiccooking.repository.redis;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.lanya.magiccooking.entity.redis.RedisUser;

public interface RedisUserRepository extends KeyValueRepository<RedisUser, String> {
}
