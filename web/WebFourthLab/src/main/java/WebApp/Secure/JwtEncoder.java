package WebApp.Secure;

import java.util.Optional;

public interface JwtEncoder {
    Optional<String> generateToken(String username);

    Optional<String> validateTokenAndRetrieveUsername(String token);
}
