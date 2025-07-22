package com.mudaya.mudaya.presentation.controllers;

import com.mudaya.mudaya.domain.entities.Vehicle;
import com.mudaya.mudaya.domain.managers.VehicleManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleManager vehicleManager;

    public VehicleController(VehicleManager vehicleManager) {
        this.vehicleManager = vehicleManager;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAll() {
        return ResponseEntity.ok(vehicleManager.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getOne(@PathVariable UUID id) {
        return vehicleManager.getOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<Vehicle> getOneByField(
            @RequestParam String field,
            @RequestParam String value
    ) {
        return vehicleManager.getOneByField(field, value)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping
    public ResponseEntity<Vehicle> create(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(vehicleManager.create(vehicle));
    }

    @PutMapping
    public ResponseEntity<UUID> update(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(vehicleManager.update(vehicle));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Vehicle vehicle) {
        vehicleManager.delete(vehicle);
        return ResponseEntity.noContent().build();
    }
}
