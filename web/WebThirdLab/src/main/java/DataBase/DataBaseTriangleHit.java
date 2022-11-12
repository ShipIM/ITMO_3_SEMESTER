package DataBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "triangle_hit")
public class DataBaseTriangleHit extends DataBaseHit {
    public DataBaseTriangleHit() {

    }

    public DataBaseTriangleHit(float r, float x, float y, boolean result, LocalDateTime requestTime,
                               String handleTime, float sideLength) {
        super(r, x, y, result, requestTime, handleTime);

        this.sideLength = sideLength;
    }

    @Column(name = "side_length")
    private float sideLength;

    public float getSideLength() {
        return sideLength;
    }

    public void setSideLength(float sideLength) {
        this.sideLength = sideLength;
    }
}
