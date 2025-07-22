package com.mudaya.mudaya.domain.managers;

import com.mudaya.mudaya.domain.entities.Transporter;
import com.mudaya.mudaya.data.repositories.TransporterRepository;
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

    public Optional<Transporter> getOne(UUID id) {
        return transporterRepository.findById(id);
    }

    public Transporter create(Transporter transporter) {
        return transporterRepository.save(transporter);
    }

    public UUID update(Transporter transporter) {
        if (!transporterRepository.existsById(transporter.getId())) {
            throw new RuntimeException("Transporter no encontrado");
        }
        transporterRepository.save(transporter);
        return transporter.getId();
    }

    public void delete(Transporter transporter) {
        if (!transporterRepository.existsById(transporter.getId())) {
            throw new RuntimeException("Transporter no encontrado");
        }
        transporterRepository.deleteById(transporter.getId());
    }
}
