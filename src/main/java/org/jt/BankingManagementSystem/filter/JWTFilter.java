package org.jt.BankingManagementSystem.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jt.BankingManagementSystem.service.IJWTService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final IJWTService service;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var path = request.getRequestURI();

        if (path.contains("login") || path.contains("register") || path.contains("create")){
            filterChain.doFilter(request, response);
            var passedPaths = List.of("login", "register","create","swagger","api-doc");
            for (var passedPath : passedPaths){
                if (path.contains(passedPath)){
                    filterChain.doFilter(request, response);
                    return;
                }
            }
        }
        String token = request.getHeader("Authorization");
//        String accountNumber = service.generateToken(token.substring(7));
//        request.setAttribute("accountNumber",accountNumber);
//        filterChain.doFilter(request, response);
    }
}
