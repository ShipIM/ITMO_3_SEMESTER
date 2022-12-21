package WebApp.DataBase.Entities;

import WebApp.Functional.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hit")
public class DataBaseHit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "r")
    private String r;

    @Column(name = "x")
    private String x;

    @Column(name = "y")
    private String y;

    @Column(name = "result")
    @Enumerated(EnumType.STRING)
    private Result.ResultVariations result;

    @Column(name = "request_time")
    private ZonedDateTime requestTime;

    @Column(name = "handle_time")
    private String handleTime;

    @Column(name = "owner")
    private String username;

    public DataBaseHit(String r, String x, String y, Result.ResultVariations result,
                       ZonedDateTime requestTime, String handleTime, String username) {
        this.r = r;
        this.x = x;
        this.y = y;
        this.result = result;
        this.requestTime = requestTime;
        this.handleTime = handleTime;
        this.username = username;
    }
}
