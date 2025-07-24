package com.mudaya.mudaya.presentation.controllers;

import com.mudaya.mudaya.domain.entities.Client;
import com.mudaya.mudaya.domain.managers.ClientManager;
import com.mudaya.mudaya.presentation.utils.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientManager clientManager;

    public ClientController(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Client>>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) Integer limit
    ) {
        List<Client> clients = clientManager.getAll(filter, limit);
        return ResponseEntity.ok(ApiResponse.success("Cleintes encontrados!", clients));
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<ApiResponse<Client>> getOne(@PathVariable UUID id) {
        Client client = clientManager.getOne(id);
        return ResponseEntity.ok(ApiResponse.success("Cleinte encontrado!", client));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Client>> create(@RequestBody Client client) {
        Client created = clientManager.create(client);
        return ResponseEntity.ok(ApiResponse.success("Cliente creado", created));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UUID>> update(@RequestBody Client client) {
        UUID updatedId = clientManager.update(client);
        return ResponseEntity.ok(ApiResponse.success("Cliente actualizado", updatedId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        clientManager.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Cliente eliminado"));
    }
}
