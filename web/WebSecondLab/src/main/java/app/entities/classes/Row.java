package app.entities.classes;

import app.entities.interfaces.Resultable;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Row {
    private final Resultable<?> resultable;
    private final Timer timer;
    private final ZonedDateTime zonedTime;

    private final String r;

    private final String x;

    private final String y;

    public Row(Resultable<?> resultable, Timer timer, ZonedDateTime zonedTime, String r, String x, String y) {
        this.resultable = resultable;
        this.timer = timer;
        this.zonedTime = zonedTime;
        this.r = r;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        String r = this.r.substring(0, Math.min(6, this.r.length()));
        String x = this.x.substring(0, Math.min(6, this.x.length()));
        String y = this.y.substring(0, Math.min(6, this.y.length()));

        return "<tr>" + "<td class = result_r>" + r + "</td>"
                + "<td class = result_x>" + x + "</td>"
                + "<td class = result_y>" + y + "</td>"
                + "<td class='result_verdict'>" + resultable.result() + "</td>"
                + "<td>" + DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm z").format(zonedTime) + "</td>"
                + "<td>" + timer.time() + "</td></tr>";
    }
}
