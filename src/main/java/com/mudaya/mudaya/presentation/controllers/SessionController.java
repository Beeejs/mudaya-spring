package com.mudaya.mudaya.presentation.controllers;

import com.mudaya.mudaya.domain.entities.User;
import com.mudaya.mudaya.domain.managers.SessionManager;
import com.mudaya.mudaya.utils.PasswordUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
public class SessionController {

    private final SessionManager sessionManager;

    public SessionController(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            User loggedInUser = sessionManager.login(user);
            return ResponseEntity.ok(loggedInUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User registeredUser = sessionManager.register(user);
            return ResponseEntity.ok(registeredUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        sessionManager.logout();
        return ResponseEntity.ok("Sesión cerrada correctamente");
    }

    @GetMapping("/current")
    public ResponseEntity<?> current() {
        User current = sessionManager.current();
        if (current != null) {
            return ResponseEntity.ok(current);
        } else {
            return ResponseEntity.status(401).body("No hay sesión activa");
        }
    }
}
