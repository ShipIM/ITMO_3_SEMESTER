package app.servlets;

import app.entities.classes.RowSession;
import app.entities.classes.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String x = Optional.ofNullable(request.getParameter("x")).orElse("");
        String y = Optional.ofNullable(request.getParameter("y")).orElse("");
        String r = Optional.ofNullable(request.getParameter("r")).orElse("");
        String type = Optional.ofNullable(request.getParameter("type")).orElse("");
        String time = Optional.ofNullable(request.getParameter("time")).orElse("");

        NumberContainer xContainer = new NumberContainer(x);
        NumberContainer yContainer = new NumberContainer(y);
        NumberContainer rContainer = new NumberContainer(r);

        CachedContainable<Float> xCached;
        CachedContainable<Float> yCached;
        CachedContainable<Float> rCached;

        if (type.equals("picture")) {
            xCached = new CachedContainable<>(xContainer);
            yCached = new CachedContainable<>(yContainer);
            rCached = new CachedContainable<>(rContainer);
        } else {
            RadioContainer xField = new RadioContainer(xContainer,
                    Arrays.asList(-5f, -4f, -3f, -2f, -1f, 0f, 1f, 2f, 3f));
            TextFieldContainer yField = new TextFieldContainer(yContainer, -3, 5);
            RadioContainer rField = new RadioContainer(rContainer, Arrays.asList(1f, 2f, 3f, 4f, 5f));

            xCached = new CachedContainable<>(xField);
            yCached = new CachedContainable<>(yField);
            rCached = new CachedContainable<>(rField);
        }

        Hit hit = new Hit(xCached, yCached, rCached);
        CachedResultable<String> hitCached = new CachedResultable<>(hit);
        Timer timer = new Timer(hitCached);
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of(time));

        Row row = new Row(hitCached, timer, dateTime, r, x, y);

        RowSession session = new RowSession(request.getSession());
        List<String> rowsList = session.getRows("rows");
        String html = row.toString();
        rowsList.add(html);
        session.setRows("rows", rowsList);

        request.setAttribute("row", html);

        request.getRequestDispatcher("views/result.jsp").forward(request, response);
    }
}
