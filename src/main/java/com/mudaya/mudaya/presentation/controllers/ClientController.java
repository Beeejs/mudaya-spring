package com.mudaya.mudaya.presentation.controllers;

import com.mudaya.mudaya.domain.entities.Client;
import com.mudaya.mudaya.domain.managers.ClientManager;
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
    public ResponseEntity<List<Client>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) Integer limit
    ) {
        return ResponseEntity.ok(clientManager.getAll(filter, limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(clientManager.getOne(id));
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client) {
        return ResponseEntity.ok(clientManager.create(client));
    }

    @PutMapping
    public ResponseEntity<UUID> update(@RequestBody Client client) {
        return ResponseEntity.ok(clientManager.update(client));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Client client) {
        clientManager.delete(client);
        return ResponseEntity.ok().build();
    }
}
