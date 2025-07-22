package com.mudaya.mudaya.domain.managers;

import com.mudaya.mudaya.domain.entities.Cotization;
import com.mudaya.mudaya.data.repositories.CotizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CotizationManager {

    private final CotizationRepository cotizationRepository;

    public CotizationManager(CotizationRepository cotizationRepository) {
        this.cotizationRepository = cotizationRepository;
    }

    public List<Cotization> getAll(String filter, Integer limit) {
        List<Cotization> cotizations;

        if (filter != null && !filter.trim().isEmpty()) {
            try {
                UUID clientId = UUID.fromString(filter);
                cotizations = cotizationRepository.findByOriginAddressContainingIgnoreCaseOrClient_Id(filter, clientId);
            } catch (IllegalArgumentException e) {
                cotizations = cotizationRepository.findByOriginAddressContainingIgnoreCaseOrClient_Id(filter, null);
            }
        } else {
            cotizations = cotizationRepository.findAll();
        }

        if (limit != null && limit > 0 && limit < cotizations.size()) {
            return cotizations.subList(0, limit);
        }

        return cotizations;
    }

    public Cotization getOne(UUID id) {
        return cotizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cotización no encontrada"));
    }

    public Cotization create(Cotization cotization) {
        if (cotization.getId() == null) {
            cotization.setId(UUID.randomUUID());
        }
        return cotizationRepository.save(cotization);
    }

    public UUID update(Cotization cotization) {
        if (!cotizationRepository.existsById(cotization.getId())) {
            throw new RuntimeException("La cotización no existe");
        }
        return cotizationRepository.save(cotization).getId();
    }

    public void delete(Cotization cotization) {
        cotizationRepository.deleteById(cotization.getId());
    }
}
