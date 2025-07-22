package com.mudaya.mudaya.data.repositories;

import com.mudaya.mudaya.domain.entities.Cotization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CotizationRepository extends JpaRepository<Cotization, UUID> {
    List<Cotization> findByOriginAddressContainingIgnoreCaseOrClient_Id(String origin, UUID clientId);
}
