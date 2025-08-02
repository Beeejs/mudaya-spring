package com.mudaya.mudaya.config;

import com.mudaya.mudaya.domain.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String uri = request.getRequestURI();
        // rutas públicas…
        if (uri.startsWith("/login")
                || uri.startsWith("/register")
                || uri.startsWith("/signout")
                || uri.startsWith("/css/")
                || uri.startsWith("/js/")
                || uri.startsWith("/images/")) {
            return true;
        }

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            response.sendRedirect("/login");
            return false;
        }

        User current = (User) session.getAttribute("currentUser");
        String role = String.valueOf(current.getRole().getName());

        // Si es STAFF, bloquear /vehicles/* y /transporters/*
        if ("STAFF".equalsIgnoreCase(role)) {
            if (uri.startsWith("/vehicles") || uri.startsWith("/transporters")) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }
        }

        // ADMIN tiene acceso a todoo
        return true;
    }

}
