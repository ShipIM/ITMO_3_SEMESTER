package app.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher;

        if (request.getParameterMap().containsKey("x") || request.getParameterMap().containsKey("y")
                || request.getParameterMap().containsKey("r")) {
            requestDispatcher = request.getRequestDispatcher("/check");
        } else {
            requestDispatcher = request.getRequestDispatcher("views/index.jsp");
        }

        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/index.jsp").forward(request, response);
    }
}
