package app.entities.classes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RowSession {
    private final HttpSession session;

    public RowSession(HttpSession session) {
        this.session = session;
    }

    public List<String> getRows(String key) {
        String[] rows = (String[]) Optional.ofNullable(session.getAttribute(key)).orElseGet(() -> new String[0]);

        return new ArrayList<>(Arrays.asList(rows));
    }

    public void setRows(String key, List<String> rows) {
        session.setAttribute(key, rows.toArray(new String[0]));
    }
}