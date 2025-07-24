package com.mudaya.mudaya.presentation.controllers;

import com.mudaya.mudaya.domain.entities.Move;
import com.mudaya.mudaya.domain.enums.MovingState;
import com.mudaya.mudaya.domain.managers.MoveManager;
import com.mudaya.mudaya.presentation.utils.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/moves")
public class MoveController {

    private final MoveManager moveManager;

    public MoveController(MoveManager moveManager) {
        this.moveManager = moveManager;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Move>>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) LocalDate dateFilter,
            @RequestParam(required = false) MovingState stateFilter,
            @RequestParam(required = false) Integer limit
    ) {
        List<Move> list = moveManager.getAll(filter, dateFilter, stateFilter, limit);
        return ResponseEntity.ok(ApiResponse.success("Mudanzas encontradas", list));
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<ApiResponse<Move>> getOne(@PathVariable UUID id) {
        Move move = moveManager.getOne(id);
        return ResponseEntity.ok(ApiResponse.success("Mudanza encontrada", move));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Move>> create(@RequestBody Move move) {
        Move created = moveManager.create(move);
        return ResponseEntity.ok(ApiResponse.success("Mudanza creada correctamente", created));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UUID>> update(@RequestBody Move move) {
        UUID updatedId = moveManager.update(move);
        return ResponseEntity.ok(ApiResponse.success("Mudanza actualizada correctamente", updatedId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        moveManager.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Mudanza eliminada correctamente"));
    }
}
