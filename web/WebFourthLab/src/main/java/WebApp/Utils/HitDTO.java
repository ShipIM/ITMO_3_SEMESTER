package WebApp.Utils;

import WebApp.DataBase.Entities.DataBaseHit;
import WebApp.Functional.Result;
import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Data;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class HitDTO {
    private String r;
    private String x;
    private String y;
    private Result.ResultVariations result;
    private ZonedDateTime requestTime;
    private String handleTime;
    private String username;

    public HitDTO(DataBaseHit hit) {
        this.r = hit.getR();
        this.x = hit.getX();
        this.y = hit.getY();
        this.handleTime = hit.getHandleTime();
        this.requestTime = hit.getRequestTime();
        this.result = hit.getResult();
        this.username = hit.getUsername();
    }

    @JsonGetter("requestTime")
    public String formattedRequestTime() {
        return this.requestTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
