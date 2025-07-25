package com.mudaya.mudaya.presentation.controllers;

import com.mudaya.mudaya.domain.entities.Transporter;
import com.mudaya.mudaya.domain.entities.User;
import com.mudaya.mudaya.domain.managers.TransporterManager;
import com.mudaya.mudaya.presentation.utils.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transporters")
public class TransporterController {

    private final TransporterManager transporterManager;

    public TransporterController(TransporterManager transporterManager) {
        this.transporterManager = transporterManager;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Transporter>>> getAll() {
        List<Transporter> transporters = transporterManager.getAll();
        return ResponseEntity.ok(ApiResponse.success("Transportistas encontrados", transporters));
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<ApiResponse<Transporter>> getOne(@PathVariable UUID id) {
        Transporter transporter = transporterManager.getOne(id);
        return ResponseEntity.ok(ApiResponse.success("Transportista encontrado", transporter));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Transporter>> create(@RequestBody Transporter transporter) {
        Transporter created = transporterManager.create(transporter);
        return ResponseEntity.ok(ApiResponse.success("Transportista creado", created));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UUID>> update(@RequestBody Transporter transporter) {
        UUID updatedId = transporterManager.update(transporter);
        return ResponseEntity.ok(ApiResponse.success("Transportista actualizado", updatedId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        transporterManager.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Transportista eliminado"));
    }
}
