package DataBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "star_hit")
public class DataBaseStarHit extends DataBaseHit {
    public DataBaseStarHit() {

    }

    public DataBaseStarHit(float r, float x, float y, boolean result, LocalDateTime requestTime,
                           String handleTime, int rayAmount) {
        super(r, x, y, result, requestTime, handleTime);

        this.rayAmount = rayAmount;
    }

    @Column(name = "ray_amount")
    private int rayAmount;

    public int getRayAmount() {
        return rayAmount;
    }

    public void setRayAmount(int rayAmount) {
        this.rayAmount = rayAmount;
    }
}
