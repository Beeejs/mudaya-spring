package com.mudaya.mudaya.domain.managers;

import com.mudaya.mudaya.domain.entities.Move;
import com.mudaya.mudaya.domain.enums.MovingState;
import com.mudaya.mudaya.data.repositories.MoveRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class MoveManager {

    private final MoveRepository moveRepository;

    public MoveManager(MoveRepository moveRepository) {
        this.moveRepository = moveRepository;
    }

    public List<Move> getAll(String filter, LocalDate date, MovingState state, Integer limit) {
        List<Move> result = moveRepository.buscarFiltrado(filter, date, state);
        if (limit != null && limit > 0 && result.size() > limit) {
            return result.subList(0, limit);
        }
        return result;
    }

    public Move getOne(UUID id) {
        return moveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mudanza no encontrada"));
    }

    public Move create(Move move) {
        if (move.getId() == null) {
            move.setId(UUID.randomUUID());
        }
        return moveRepository.save(move);
    }

    public UUID update(Move move) {
        if (!moveRepository.existsById(move.getId())) {
            throw new RuntimeException("Mudanza no existe");
        }
        return moveRepository.save(move).getId();
    }

    public void delete(Move move) {
        moveRepository.deleteById(move.getId());
    }
}
