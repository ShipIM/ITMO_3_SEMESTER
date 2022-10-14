package app.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unchecked")
public class HeadersFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Object object = context.getAttribute("headers");
        ConcurrentHashMap<String, Long> headers;

        if (object != null) {
            headers = (ConcurrentHashMap<String, Long>) object;
        } else {
            headers = new ConcurrentHashMap<>();
            context.setAttribute("headers", headers);
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Enumeration<String> names = httpRequest.getHeaderNames();
        Collections.list(names).forEach((name) -> headers.merge(name, 1L, Long::sum));

        StringBuilder stringBuilder = new StringBuilder();
        headers.forEach((key, value) -> stringBuilder.append(key).append(" = ").append(value).append("\n"));

        System.out.println(stringBuilder);

        chain.doFilter(request, response);
    }
}
