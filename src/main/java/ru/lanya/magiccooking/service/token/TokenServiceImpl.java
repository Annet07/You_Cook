package ru.lanya.magiccooking.service.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.lanya.magiccooking.dto.TokenDto;
import ru.lanya.magiccooking.entity.User;
import ru.lanya.magiccooking.entity.token.RefreshToken;
import ru.lanya.magiccooking.form.UserForm;
import ru.lanya.magiccooking.repository.token.TokenRepository;
import ru.lanya.magiccooking.repository.UserRepository;
import ru.lanya.magiccooking.service.redis.RedisUserService;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${access.token.lifetime}")
    private Long accessLifetime;

    @Value("${refresh.token.lifetime}")
    private Long refreshLifetime;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private RedisUserService redisUserService;

    @Value("${token.secret.key}")
    private String secretKey;

    @SneakyThrows
    @Override
    public TokenDto getNewTokens(User user) {
        String accessToken = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("role", user.getRole().toString())
                .withClaim("email", user.getEmail())
                .withClaim("username", user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+accessLifetime))
                .sign(Algorithm.HMAC256(secretKey));
        String refreshToken = UUID.randomUUID().toString();
        RefreshToken token = RefreshToken.builder()
                .refreshToken(refreshToken)
                .user(user)
                .expiredTime(new Date(System.currentTimeMillis()+refreshLifetime))
                .build();
        Optional<RefreshToken> oldRefreshToken = tokenRepository.findByUser(user);
        oldRefreshToken.ifPresent(value -> tokenRepository.delete(value));
        tokenRepository.save(token);
        redisUserService.addTokenToUser(user, accessToken, refreshToken);
        return TokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }

    public Optional<RefreshToken> findByRefreshToken(String token) {
        return tokenRepository.findByRefreshToken(token);
    }

    @Override
    public String updateAccessToken(String stringRefreshToken) {
        Optional<RefreshToken> refreshToken = tokenRepository.findByRefreshToken(stringRefreshToken);
        if (refreshToken.isPresent()){
            User user = refreshToken.get().getUser();
            return JWT.create()
                    .withSubject(user.getId().toString())
                    .withClaim("role", user.getRole().toString())
                    .withClaim("email", user.getEmail())
                    .withClaim("username", user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis()+accessLifetime))
                    .sign(Algorithm.HMAC256(secretKey));
        }
        throw new BadCredentialsException("Token cannot be renewed!");
    }

    @Override
    public Optional<User> findByAccessToken(String accessToken) {
        DecodedJWT decodedJWT = (DecodedJWT) JWT.require(Algorithm.HMAC256(secretKey));
        return userRepository.findByEmail(decodedJWT.getClaim("email").asString());
    }
}
