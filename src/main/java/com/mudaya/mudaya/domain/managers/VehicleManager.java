package com.mudaya.mudaya.domain.managers;

import com.mudaya.mudaya.data.repositories.VehicleRepository;
import com.mudaya.mudaya.domain.entities.Vehicle;
import com.mudaya.mudaya.presentation.utils.exceptions.ApiException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleManager {

    private final VehicleRepository vehicleRepository;

    public VehicleManager(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle getOne(UUID id) {
        return vehicleRepository.findById(id)
            .orElseThrow(() -> new ApiException("Vehiculo no encontrado"));
    }

    public Optional<Vehicle> getOneByField(String field, String value) {
        if ("license_plate".equalsIgnoreCase(field)) {
            return vehicleRepository.findByLicensePlateIgnoreCase(value);
        }

        throw new IllegalArgumentException("Campo no soportado para búsqueda: " + field);
    }

    public Vehicle create(Vehicle vehicle) {
        boolean exists = vehicleRepository.findByLicensePlateIgnoreCase(vehicle.getLicensePlate()).isPresent();
        if (exists) {
            throw new ApiException("Ya existe un vehículo con la misma patente");
        }
        return vehicleRepository.save(vehicle);
    }

    public UUID update(Vehicle vehicle) {
        return vehicleRepository.save(vehicle).getId();
    }

    public void delete(UUID id) {
        if (!vehicleRepository.existsById(id)) {
            throw new ApiException("El vehiculo no existe");
        }
        vehicleRepository.deleteById(id);
    }
}
