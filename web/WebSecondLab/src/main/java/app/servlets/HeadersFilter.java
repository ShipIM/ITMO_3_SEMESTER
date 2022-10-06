package app.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HeadersFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        ConcurrentHashMap<String, Long> headers = new ConcurrentHashMap<>();

        Object tmp = context.getAttribute("headers");
        if (tmp != null) {
            if (tmp instanceof ConcurrentHashMap<?, ?>) {
                ConcurrentHashMap<?, ?> map = (ConcurrentHashMap<?, ?>) tmp;

                map.forEach((key, value) -> {
                    if (key instanceof String && value instanceof Long) {
                        headers.put((String) key, (Long) value);
                    }
                });
            }
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Enumeration<String> names = httpRequest.getHeaderNames();

        Collections.list(names).forEach((name) -> headers.merge(name, 1L, Long::sum));

        context.setAttribute("headers", headers);

        StringBuilder stringBuilder = new StringBuilder();
        headers.forEach((key, value) -> stringBuilder.append(key).append(" = ").append(value).append("\n"));

        System.out.println(stringBuilder);

        chain.doFilter(request, response);
    }
}
