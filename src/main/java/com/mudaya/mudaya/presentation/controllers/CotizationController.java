package com.mudaya.mudaya.presentation.controllers;

import com.mudaya.mudaya.domain.entities.Cotization;
import com.mudaya.mudaya.domain.managers.CotizationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cotizations")
public class CotizationController {

    private final CotizationManager cotizationManager;

    public CotizationController(CotizationManager cotizationManager) {
        this.cotizationManager = cotizationManager;
    }

    @GetMapping
    public ResponseEntity<List<Cotization>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) Integer limit
    ) {
        return ResponseEntity.ok(cotizationManager.getAll(filter, limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cotization> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(cotizationManager.getOne(id));
    }

    @PostMapping
    public ResponseEntity<Cotization> create(@RequestBody Cotization cotization) {
        return ResponseEntity.ok(cotizationManager.create(cotization));
    }

    @PutMapping
    public ResponseEntity<UUID> update(@RequestBody Cotization cotization) {
        return ResponseEntity.ok(cotizationManager.update(cotization));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Cotization cotization) {
        cotizationManager.delete(cotization);
        return ResponseEntity.ok().build();
    }
}
