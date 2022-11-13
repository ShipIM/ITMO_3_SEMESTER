package CustomServlets;

import DataBase.HitService;
import DataBase.StarHitDAO;
import DataBase.TriangleHitDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

public class HitDataServlet extends HttpServlet {
    public HitService service;

    @Override
    public void init(ServletConfig config) {
        this.service = new HitService(new TriangleHitDAO(), new StarHitDAO());
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        PrintWriter writer = response.getWriter();
        writer.println(mapper.writeValueAsString(service.getAll()));
    }
}
