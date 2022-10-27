package Functional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@ManagedBean
@ViewScoped
public class ClockBean implements Serializable {
    private LocalDateTime time;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            .withZone(ZoneId.of("GMT"));

    public ClockBean() {
        time = LocalDateTime.now();
    }

    public void updateTime() {
        this.time = LocalDateTime.now();
    }

    public String getTime() {
        return this.time.format(formatter);
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}