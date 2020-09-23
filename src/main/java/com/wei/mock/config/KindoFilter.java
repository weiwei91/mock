package com.wei.mock.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@WebFilter
public class KindoFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURL = request.getRequestURL().toString();
        String protocol = requestURL.split("://")[0];

        if ("http".equals(protocol)) {
            requestURL = requestURL.replace("http", "https").replace("8081", "8080");
            response.sendRedirect(requestURL);
        }
        filterChain.doFilter(request, response);
    }
}
