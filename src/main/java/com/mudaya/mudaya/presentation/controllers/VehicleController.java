package com.mudaya.mudaya.presentation.controllers;

import com.mudaya.mudaya.domain.entities.User;
import com.mudaya.mudaya.domain.entities.Vehicle;
import com.mudaya.mudaya.domain.managers.VehicleManager;
import com.mudaya.mudaya.presentation.utils.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<Vehicle>>> getAll() {
        List<Vehicle> vehicles = vehicleManager.getAll();
        return ResponseEntity.ok(ApiResponse.success("Vehículos encontrados", vehicles));
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<ApiResponse<Vehicle>> getOne(@PathVariable UUID id) {
        Vehicle vehicle = vehicleManager.getOne(id);
        return ResponseEntity.ok(ApiResponse.success("Vehículo encontrado", vehicle));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Optional<Vehicle>>> getOneByField(
            @RequestParam String field,
            @RequestParam String value
    ) {
        Optional<Vehicle> vehicle = vehicleManager.getOneByField(field, value);
        return ResponseEntity.ok(ApiResponse.success("Vehiculo encontrado", vehicle));

    }

    @PostMapping
    public ResponseEntity<ApiResponse<Vehicle>> create(@RequestBody Vehicle vehicle) {
        Vehicle created = vehicleManager.create(vehicle);
        return ResponseEntity.ok(ApiResponse.success("Vehículo creado", created));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UUID>> update(@RequestBody Vehicle vehicle) {
        UUID updatedId = vehicleManager.update(vehicle);
        return ResponseEntity.ok(ApiResponse.success("Vehículo actualizado", updatedId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        vehicleManager.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Vehículo eliminado"));
    }
}
