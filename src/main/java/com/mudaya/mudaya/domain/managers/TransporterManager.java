package com.mudaya.mudaya.domain.managers;

import com.mudaya.mudaya.domain.entities.Transporter;
import com.mudaya.mudaya.data.repositories.TransporterRepository;
import com.mudaya.mudaya.presentation.utils.exceptions.ApiException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransporterManager {

    private final TransporterRepository transporterRepository;

    public TransporterManager(TransporterRepository transporterRepository) {
        this.transporterRepository = transporterRepository;
    }

    public List<Transporter> getAll() {
        return transporterRepository.findAll();
    }

    public Transporter getOne(UUID id) {
        return transporterRepository.findById(id)
            .orElseThrow(() -> new ApiException("Transporter no encontrado"));
    }

    public Transporter create(Transporter transporter) {
        return transporterRepository.save(transporter);
    }

    public UUID update(Transporter transporter) {
        if (!transporterRepository.existsById(transporter.getId())) {
            throw new ApiException("Transporter no encontrado");
        }
        transporterRepository.save(transporter);
        return transporter.getId();
    }

    public void delete(UUID id) {
        if (!transporterRepository.existsById(id)) {
            throw new ApiException("El transportador no existe");
        }
        transporterRepository.deleteById(id);
    }
}
