package com.mudaya.mudaya.presentation.controllers;

import com.mudaya.mudaya.domain.entities.Role;
import com.mudaya.mudaya.domain.managers.RoleManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleManager roleManager;

    public RoleController(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAll() {
        return ResponseEntity.ok(roleManager.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<Role> getOneByField(
            @RequestParam String field,
            @RequestParam String value
    ) {
        try {
            Role role = roleManager.getOneByField(field, value);
            return ResponseEntity.ok(role);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Role> create(@RequestBody Role role) {
        return ResponseEntity.ok(roleManager.create(role));
    }

    @PutMapping
    public ResponseEntity<UUID> update(@RequestBody Role role) {
        return ResponseEntity.ok(roleManager.update(role));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        roleManager.delete(id);
        return ResponseEntity.ok().build();
    }
}
