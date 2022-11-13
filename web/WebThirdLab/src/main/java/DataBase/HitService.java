package DataBase;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ManagedBean
@ApplicationScoped
public class HitService implements Serializable {

    @ManagedProperty(value = "#{triangleHitDAO}")
    private DAO<DataBaseTriangleHit, Integer> triangleHit;

    @ManagedProperty(value = "#{starHitDAO}")
    private DAO<DataBaseStarHit, Integer> starHit;

    public HitService() {

    }

    public HitService(DAO<DataBaseTriangleHit, Integer> triangleHit, DAO<DataBaseStarHit, Integer> starHit) {
        this.triangleHit = triangleHit;
        this.starHit = starHit;
    }

    public List<DataBaseHit> getAll() {
        return Stream.concat(triangleHit.getAll().stream(), starHit.getAll().stream()).collect(Collectors.toList());
    }

    public void create(DataBaseStarHit hit) {
        starHit.create(hit);
    }

    public void create(DataBaseTriangleHit hit) {
        triangleHit.create(hit);
    }

    public DAO<DataBaseStarHit, Integer> getStarHit() {
        return starHit;
    }

    public DAO<DataBaseTriangleHit, Integer> getTriangleHit() {
        return triangleHit;
    }

    public void setStarHit(DAO<DataBaseStarHit, Integer> starHit) {
        this.starHit = starHit;
    }

    public void setTriangleHit(DAO<DataBaseTriangleHit, Integer> triangleHit) {
        this.triangleHit = triangleHit;
    }
}
