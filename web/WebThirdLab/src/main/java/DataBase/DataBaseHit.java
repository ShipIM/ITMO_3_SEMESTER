package DataBase;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "hits")
public class DataBaseHit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "r")
    private float r;
    @Column(name = "x")
    private float x;
    @Column(name = "y")
    private float y;
    @Column(name = "result")
    private boolean result;
    @Column(name = "request_time")
    private LocalDateTime request_time;
    @Column(name = "handle_time")
    private String handle_time;

    public DataBaseHit() {

    }

    public DataBaseHit(float r, float x, float y, boolean result, LocalDateTime request_time,
                       String handle_time) {
        this.r = r;
        this.x = x;
        this.y = y;
        this.result = result;
        this.request_time = request_time;
        this.handle_time = handle_time;
    }

    public float getR() {
        return r;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isResult() {
        return result;
    }

    public LocalDateTime getRequest_time() {
        return request_time;
    }

    public String getHandle_time() {
        return handle_time;
    }

    public String formattedRequestTime() {
        return this.request_time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
