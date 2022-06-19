package ru.lanya.magiccooking.repository.token;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lanya.magiccooking.entity.User;
import ru.lanya.magiccooking.entity.token.RefreshToken;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(String token);
    void delete(RefreshToken refreshToken);
    Optional<RefreshToken> findByUser(User user);
}
