package com.kodilla.ecommercee;

import com.kodilla.ecommercee.service.SecurityService;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class SecurityFilter implements Filter {

    private final SecurityService securityService;

    public SecurityFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        if(req.getMethod().equals("GET")) {
            chain.doFilter(request, response);
            return;
        }

        checkAuth(req);

        chain.doFilter(request, response);

    }

    private void checkAuth(HttpServletRequest req) {
        Long userId = Long.valueOf(req.getHeader("X_USER_ID"));
        String accessKey = req.getHeader("X_ACCESS_KEY");
        securityService.validateAccess(userId, accessKey);
    }
}
