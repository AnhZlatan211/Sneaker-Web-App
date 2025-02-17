package project01.ecommerce.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var authorities = authentication.getAuthorities();
        boolean isAdmin = authorities.stream().anyMatch(r -> r.getAuthority().equals("ADMIN"));
        boolean isUser = authorities.stream().anyMatch(r -> r.getAuthority().equals("USER"));

        if(isAdmin) {
            response.sendRedirect("/admin");
        }
        else if (isUser) {
            response.sendRedirect("/home");
        }
        else {
            response.sendRedirect("/error");
        }
    }
}
