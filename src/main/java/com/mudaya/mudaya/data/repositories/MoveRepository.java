package com.mudaya.mudaya.data.repositories;

import com.mudaya.mudaya.domain.entities.Move;
import com.mudaya.mudaya.domain.enums.MovingState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MoveRepository extends JpaRepository<Move, UUID> {

    @Query("""
        SELECT m FROM Move m
        JOIN m.cotization c
        JOIN c.client cl
        WHERE (:state IS NULL OR m.state = :state)
        AND (:filter IS NULL OR LOWER(cl.name) LIKE LOWER(CONCAT('%', :filter, '%')) 
                         OR LOWER(cl.surname) LIKE LOWER(CONCAT('%', :filter, '%')))
        AND (:date IS NULL OR FUNCTION('DATE', m.movingDateTime) = :date)
        ORDER BY m.movingDateTime DESC
    """)
    List<Move> buscarFiltrado(String filter, LocalDate date, MovingState state);
}
