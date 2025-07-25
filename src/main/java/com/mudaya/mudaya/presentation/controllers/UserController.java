package com.mudaya.mudaya.presentation.controllers;

import com.mudaya.mudaya.domain.entities.User;
import com.mudaya.mudaya.domain.managers.UserManager;
import com.mudaya.mudaya.presentation.utils.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAll() {
        List<User> users = userManager.getAll();
        return ResponseEntity.ok(ApiResponse.success("Usuarios encontrados", users));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<User>> getOneByField(
            @RequestParam String field,
            @RequestParam String value
    ) {
        User user = userManager.getOneByField(field, value);
        return ResponseEntity.ok(ApiResponse.success("Usuario encontrado", user));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> create(@RequestBody User user) {
        User created = userManager.create(user);
        return ResponseEntity.ok(ApiResponse.success("Usuario creado", created));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UUID>> update(@RequestBody User user) {
        UUID updatedId = userManager.update(user);
        return ResponseEntity.ok(ApiResponse.success("Usuario actualizado", updatedId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        userManager.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Usuario eliminado"));
    }
}
