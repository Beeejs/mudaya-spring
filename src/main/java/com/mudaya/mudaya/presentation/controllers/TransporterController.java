package com.mudaya.mudaya.presentation.controllers;

import com.mudaya.mudaya.domain.entities.Transporter;
import com.mudaya.mudaya.domain.managers.TransporterManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transporters")
public class TransporterController {

    private final TransporterManager transporterManager;

    public TransporterController(TransporterManager transporterManager) {
        this.transporterManager = transporterManager;
    }

    @GetMapping
    public ResponseEntity<List<Transporter>> getAll() {
        return ResponseEntity.ok(transporterManager.getAll());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Transporter> getOne(@PathVariable UUID id) {
        return transporterManager.getOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Transporter> create(@RequestBody Transporter transporter) {
        return ResponseEntity.ok(transporterManager.create(transporter));
    }

    @PutMapping
    public ResponseEntity<UUID> update(@RequestBody Transporter transporter) {
        return ResponseEntity.ok(transporterManager.update(transporter));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        transporterManager.delete(id);
        return ResponseEntity.noContent().build();
    }
}
