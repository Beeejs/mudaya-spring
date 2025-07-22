package com.mudaya.mudaya.presentation.controllers;

import com.mudaya.mudaya.domain.entities.Move;
import com.mudaya.mudaya.domain.enums.MovingState;
import com.mudaya.mudaya.domain.managers.MoveManager;
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
    public ResponseEntity<List<Move>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) LocalDate dateFilter,
            @RequestParam(required = false) MovingState stateFilter,
            @RequestParam(required = false) Integer limit
    ) {
        return ResponseEntity.ok(moveManager.getAll(filter, dateFilter, stateFilter, limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Move> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(moveManager.getOne(id));
    }

    @PostMapping
    public ResponseEntity<Move> create(@RequestBody Move move) {
        return ResponseEntity.ok(moveManager.create(move));
    }

    @PutMapping
    public ResponseEntity<UUID> update(@RequestBody Move move) {
        return ResponseEntity.ok(moveManager.update(move));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Move move) {
        moveManager.delete(move);
        return ResponseEntity.ok().build();
    }
}
