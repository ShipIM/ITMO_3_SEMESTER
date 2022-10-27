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
    private LocalDateTime requestTime;
    @Column(name = "handle_time")
    private String handleTime;

    public DataBaseHit() {

    }

    public DataBaseHit(float r, float x, float y, boolean result, LocalDateTime requestTime,
                       String handleTime) {
        this.r = r;
        this.x = x;
        this.y = y;
        this.result = result;
        this.requestTime = requestTime;
        this.handleTime = handleTime;
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

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public String getHandleTime() {
        return handleTime;
    }

    public String formattedRequestTime() {
        return this.requestTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
