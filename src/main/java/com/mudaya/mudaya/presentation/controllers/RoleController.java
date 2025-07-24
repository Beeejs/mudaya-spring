package com.mudaya.mudaya.presentation.controllers;

import com.mudaya.mudaya.domain.entities.Role;
import com.mudaya.mudaya.domain.managers.RoleManager;
import com.mudaya.mudaya.presentation.utils.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<Role>>> getAll() {
        List<Role> roles = roleManager.getAll();
        return ResponseEntity.ok(ApiResponse.success("Roles encontrados", roles));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Role>> getOneByField(
            @RequestParam String field,
            @RequestParam String value
    ) {
        Role role = roleManager.getOneByField(field, value);
        return ResponseEntity.ok(ApiResponse.success("Rol encontrado", role));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Role>> create(@RequestBody Role role) {
        Role created = roleManager.create(role);
        return ResponseEntity.ok(ApiResponse.success("Rol creado correctamente", created));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UUID>> update(@RequestBody Role role) {
        UUID updatedId = roleManager.update(role);
        return ResponseEntity.ok(ApiResponse.success("Rol actualizado correctamente", updatedId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        roleManager.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Rol eliminado correctamente"));
    }
}
