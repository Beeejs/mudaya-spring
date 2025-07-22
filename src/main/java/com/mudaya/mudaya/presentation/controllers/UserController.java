package com.mudaya.mudaya.presentation.controllers;

import com.mudaya.mudaya.domain.entities.User;
import com.mudaya.mudaya.domain.managers.UserManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userManager.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<User> getOneByField(
            @RequestParam String field,
            @RequestParam String value
    ) {
        try {
            User user = userManager.getOneByField(field, value);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userManager.create(user));
    }

    @PutMapping
    public ResponseEntity<UUID> update(@RequestBody User user) {
        return ResponseEntity.ok(userManager.update(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userManager.delete(id);
        return ResponseEntity.noContent().build();
    }
}
