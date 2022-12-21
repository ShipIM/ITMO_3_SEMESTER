package WebApp.DataBase.Services;

import WebApp.DataBase.Entities.DataBaseUser;
import WebApp.DataBase.Repositories.UserRepository;
import WebApp.Secure.JwtEncoder;
import WebApp.Secure.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtEncoder jwt;

    public AuthService(@Autowired UserRepository userRepository, @Autowired PasswordEncoder encoder,
                       @Autowired JwtEncoder jwt) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public void register(String username, String password) {
        if (this.userRepository.getDataBaseUserByUsername(username).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пользователь с таким именем уже существует.");

        this.userRepository.save(new DataBaseUser(username, this.encoder.encode(password)));
    }

    public String login(String username, String password) {
        Optional<DataBaseUser> user = this.userRepository.getDataBaseUserByUsername(username);

        if (!user.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пользователя с таким именем не существует.");

        if (!encoder.matches(password, user.get().getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Вы указали неверный пароль.");

        Optional<String> token = jwt.generateToken(username);
        if (!token.isPresent())
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Что-то пошло не так во время генерации токена.");

        return token.get();
    }
}
