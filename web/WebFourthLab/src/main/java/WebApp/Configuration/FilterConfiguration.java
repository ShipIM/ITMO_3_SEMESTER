package WebApp.Configuration;

import WebApp.Secure.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
    private final AuthFilter auth;

    public FilterConfiguration(@Autowired AuthFilter auth) {
        this.auth = auth;
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authRegistration() {
        FilterRegistrationBean<AuthFilter> registration = new FilterRegistrationBean<>();

        registration.setFilter(auth);
        registration.addUrlPatterns("/api/hits");

        return registration;
    }
}
