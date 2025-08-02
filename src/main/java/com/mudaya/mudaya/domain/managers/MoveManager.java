package com.mudaya.mudaya.domain.managers;

import com.mudaya.mudaya.domain.entities.Move;
import com.mudaya.mudaya.domain.enums.MovingState;
import com.mudaya.mudaya.data.repositories.MoveRepository;
import com.mudaya.mudaya.presentation.utils.exceptions.ApiException;
import org.springframework.format.annotation.DateTimeFormat;
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

    public List<Move> getAll(String filter,
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                             MovingState state,
                             Integer limit) {

        List<Move> list = moveRepository.findAllByOrderByMovingDateTimeDesc();

        if (filter != null && !filter.isBlank()) {
            String f = filter.trim().toLowerCase();

            System.out.println(">>> Filtrando movimientos por: «" + f + "»");
            System.out.println("  Antes: " + list.size() + " movimientos");

            list = list.stream()
                    .peek(m -> System.out.println("    >> candidato: " +
                            m.getCotization().getClient().getName() + " " +
                            m.getCotization().getClient().getSurname()))
                    .filter(m -> {
                        String nom = m.getCotization().getClient().getName();
                        String ape = m.getCotization().getClient().getSurname();
                        return (nom  != null && nom.trim().toLowerCase().contains(f))
                                || (ape  != null && ape.trim().toLowerCase().contains(f));
                    })
                    .peek(m -> System.out.println("      -- pasa filtro: " +
                            m.getCotization().getClient().getName() + " " +
                            m.getCotization().getClient().getSurname()))
                    .toList();

            System.out.println("  Después: " + list.size() + " movimientos");
        }

        System.out.println(list.toString());
        System.out.println(filter);
        System.out.println(date);
        System.out.println(state);

        if (date != null) {
            list = list.stream()
                    .filter(m -> m.getMovingDateTime().toLocalDate().equals(date))
                    .toList();
        }

        if (state != null) {
            list = list.stream()
                    .filter(m -> m.getState() == state)
                    .toList();
        }

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
