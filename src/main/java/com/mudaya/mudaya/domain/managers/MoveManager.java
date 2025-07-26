package com.mudaya.mudaya.domain.managers;

import com.mudaya.mudaya.domain.entities.Move;
import com.mudaya.mudaya.domain.enums.MovingState;
import com.mudaya.mudaya.data.repositories.MoveRepository;
import com.mudaya.mudaya.presentation.utils.exceptions.ApiException;
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
        // 1) Traigo todo ya ordenado
        List<Move> list = moveRepository.findAllByOrderByMovingDateTimeDesc();

        // 2) Filtro por nombre/apellido de cliente si viene filter
        if (filter != null && !filter.isBlank()) {
            String f = filter.toLowerCase();
            list = list.stream()
                    .filter(m ->
                            m.getCotization().getClient().getName().toLowerCase().contains(f) ||
                                    m.getCotization().getClient().getSurname().toLowerCase().contains(f)
                    )
                    .toList();
        }

        // 3) Filtro por fecha si viene date
        if (date != null) {
            list = list.stream()
                    .filter(m -> m.getMovingDateTime().toLocalDate().equals(date))
                    .toList();
        }

        // 4) Filtro por estado si viene state
        if (state != null) {
            list = list.stream()
                    .filter(m -> m.getState() == state)
                    .toList();
        }

        // 5) Aplico límite si viene
        if (limit != null && limit > 0 && list.size() > limit) {
            return list.subList(0, limit);
        }

        return list;
    }

    public Move getOne(UUID id) {
        return moveRepository.findById(id)
            .orElseThrow(() -> new ApiException("Mudanza no encontrada"));
    }

    public Move create(Move move) {
        return moveRepository.save(move);
    }

    public UUID update(Move move) {
        if (!moveRepository.existsById(move.getId())) {
            throw new ApiException("Mudanza no existe");
        }
        return moveRepository.save(move).getId();
    }

    public void delete(UUID id) {
        if (!moveRepository.existsById(id)) {
            throw new ApiException("Mudanza no existe");
        }
        moveRepository.deleteById(id);
    }
}
