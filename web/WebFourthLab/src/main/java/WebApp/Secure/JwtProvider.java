package WebApp.Secure;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JwtProvider implements JwtEncoder {
    private final String secret;

    public JwtProvider(@Value("${jwt-secret}") String secret) {
        this.secret = secret;
    }

    public Optional<String> generateToken(String username) {
        try {
            return Optional.of(JWT.create()
                    .withClaim("username", username)
                    .sign(Algorithm.HMAC256(this.secret)));
        } catch (IllegalArgumentException | JWTCreationException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<String> validateTokenAndRetrieveUsername(String token) {
        try {
            return Optional.of(JWT.require(Algorithm.HMAC256(this.secret)).build()
                    .verify(token).getClaim("username").asString());
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
