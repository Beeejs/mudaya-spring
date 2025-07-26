package com.mudaya.mudaya.data.repositories;

import com.mudaya.mudaya.domain.entities.Move;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MoveRepository extends JpaRepository<Move, UUID> {
    /** Recupera todas las mudanzas, ya ordenadas del más reciente al más antiguo */
    List<Move> findAllByOrderByMovingDateTimeDesc();
}
