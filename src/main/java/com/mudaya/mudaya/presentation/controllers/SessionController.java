package com.mudaya.mudaya.presentation.controllers;

import com.mudaya.mudaya.domain.entities.User;
import com.mudaya.mudaya.domain.managers.SessionManager;
import com.mudaya.mudaya.presentation.utils.response.ApiResponse;
import com.mudaya.mudaya.utils.PasswordUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    private final SessionManager sessionManager;

    public SessionController(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<User>> login(@RequestBody User user) {
        try {
            User loggedInUser = sessionManager.login(user);
            return ResponseEntity.ok(ApiResponse.success("Login exitoso", loggedInUser));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Login fallido: " + e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@RequestBody User user) {
        try {
            User registeredUser = sessionManager.register(user);
            return ResponseEntity.ok(ApiResponse.success("Registro exitoso", registeredUser));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Registro fallido: " + e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout() {
        sessionManager.logout();
        return ResponseEntity.ok(ApiResponse.success("Sesión cerrada correctamente"));
    }

    @GetMapping("/current")
    public ResponseEntity<ApiResponse<User>> current() {
        User current = sessionManager.current();
        if (current != null) {
            return ResponseEntity.ok(ApiResponse.success("Sesión activa", current));
        } else {
            return ResponseEntity.status(401).body(ApiResponse.error("No hay sesión activa"));
        }
    }
}
