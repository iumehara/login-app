package com.example.filters;

import com.example.Jdbc.JdbcSessionDataMapper;
import com.example.SessionDataMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Component
public class RequestFilter implements Filter {
    private SessionDataMapper sessionDataMapper;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
        this.sessionDataMapper = context.getBean(JdbcSessionDataMapper.class);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (authorizeRequest((HttpServletRequest) request)) {
            chain.doFilter(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/unauthenticated");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {}

    private boolean authorizeRequest(HttpServletRequest request) {
        String servletName = request.getServletPath();
        String method = request.getMethod();

        if (servletName.equals("/unauthenticated") || servletName.equals("/login") || method.equalsIgnoreCase("options")) {
            return true;
        }

        String originalToken = request.getHeader("Authorization");

        if (originalToken == null) {
            return false;
        }

        String token = originalToken;
        if (originalToken.contains("Bearer")) {
            token = originalToken.replace("Bearer", "").trim();
        }

        Optional<Integer> maybeUserId = sessionDataMapper.validate(token);

        if (maybeUserId.isPresent()) {
            Integer userId = maybeUserId.get();
            request.setAttribute("userId", userId);
            return true;
        } else {
            return false;
        }
    }
}
