package com.mudaya.mudaya.presentation.controllers;

import com.mudaya.mudaya.domain.entities.Cotization;
import com.mudaya.mudaya.domain.managers.CotizationManager;
import com.mudaya.mudaya.presentation.utils.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cotizations")
public class CotizationController {

    private final CotizationManager cotizationManager;

    public CotizationController(CotizationManager cotizationManager) {
        this.cotizationManager = cotizationManager;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Cotization>>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) Integer limit
    ) {
        List<Cotization> list = cotizationManager.getAll(filter, limit);
        return ResponseEntity.ok(ApiResponse.success("Cotizaciones encontradas!", list));
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<ApiResponse<Cotization>> getOne(@PathVariable UUID id) {
        Cotization cotization = cotizationManager.getOne(id);
        return ResponseEntity.ok(ApiResponse.success("Cotización encontrada!", cotization));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Cotization>> create(@RequestBody Cotization cotization) {
        Cotization created = cotizationManager.create(cotization);
        return ResponseEntity.ok(ApiResponse.success("Cotización creada correctamente", created));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UUID>> update(@RequestBody Cotization cotization) {
        UUID updatedId = cotizationManager.update(cotization);
        return ResponseEntity.ok(ApiResponse.success("Cotización actualizada correctamente", updatedId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        cotizationManager.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Cotización eliminada correctamente"));
    }
}
