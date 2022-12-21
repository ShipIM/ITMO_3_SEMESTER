package WebApp.Secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@Order(1)
public class AuthFilter implements Filter {
    private final JwtEncoder jwt;

    public AuthFilter(@Autowired JwtEncoder jwt) {
        this.jwt = jwt;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String bearerToken = httpRequest.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);

            if (!token.equals("")) {
                Optional<String> username = jwt.validateTokenAndRetrieveUsername(token);

                if (username.isPresent()) {
                    request.setAttribute("username", username.get());
                    filterChain.doFilter(request, response);
                }
            }
        }

        if (request.getAttribute("username") == null)
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Для доступа к ресурсу необходимо авторизоваться.");
    }
}
