package WebApp.Secure;

import WebApp.Utils.Audited;
import org.springframework.stereotype.Component;

@Component
public class HashEncoder implements PasswordEncoder {
    @Override
    @Audited
    public String encode(CharSequence rawPassword) {
        return Integer.toString(rawPassword.hashCode());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return this.encode(rawPassword).equals(encodedPassword);
    }
}
