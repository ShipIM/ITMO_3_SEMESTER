package WebApp.DataBase.Repositories;

import WebApp.DataBase.Entities.DataBaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<DataBaseUser, Long> {
    Optional<DataBaseUser> getDataBaseUserByUsername(String username);
}
