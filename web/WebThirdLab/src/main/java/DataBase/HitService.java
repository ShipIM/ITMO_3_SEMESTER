package DataBase;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class HitService implements Serializable {

    @ManagedProperty(value = "#{hitDAO}")
    private DAO<DataBaseHit, Integer> hit;

    public List<DataBaseHit> getAll() {
        return hit.getAll();
    }

    public DataBaseHit getEntity(int key) {
        return hit.getEntityById(key);
    }

    public void delete(int key) {
        this.hit.delete(key);
    }

    public void create(DataBaseHit hit) {
        this.hit.create(hit);
    }

    public DAO<DataBaseHit, Integer> getHit() {
        return hit;
    }

    public void setHit(DAO<DataBaseHit, Integer> hit) {
        this.hit = hit;
    }
}
