package WebApp.DataBase.Repositories;

import WebApp.DataBase.Entities.DataBaseHit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HitRepository extends JpaRepository<DataBaseHit, Long> {
    List<DataBaseHit> getDataBaseHitsByUsername(String username);
}
