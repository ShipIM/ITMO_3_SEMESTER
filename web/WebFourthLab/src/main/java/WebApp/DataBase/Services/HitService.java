package WebApp.DataBase.Services;

import WebApp.DataBase.Entities.DataBaseHit;
import WebApp.DataBase.Repositories.HitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HitService {
    private final HitRepository hitRepository;

    public HitService(@Autowired HitRepository hitRepository) {
        this.hitRepository = hitRepository;
    }

    public void addHit(DataBaseHit hit) {
        hitRepository.save(hit);
    }

    public List<DataBaseHit> getAll(String username) {
        return hitRepository.getDataBaseHitsByUsername(username);
    }
}
