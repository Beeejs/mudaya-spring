package com.mudaya.mudaya.domain.managers;

import com.mudaya.mudaya.domain.entities.Role;
import com.mudaya.mudaya.domain.enums.UserRol;
import com.mudaya.mudaya.data.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleManager {

    private final RoleRepository roleRepository;

    public RoleManager(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role getOneByField(String field, String value) {
        return switch (field) {
            case "id" -> roleRepository.findById(UUID.fromString(value))
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            case "name" -> roleRepository.findByName(UserRol.valueOf(value.toUpperCase()))
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            default -> throw new IllegalArgumentException("Campo inválido para búsqueda de rol");
        };
    }

    public Role create(Role role) {
        if (role.getId() == null) {
            role.setId(UUID.randomUUID());
        }
        return roleRepository.save(role);
    }

    public UUID update(Role role) {
        if (!roleRepository.existsById(role.getId())) {
            throw new RuntimeException("El rol no existe");
        }
        return roleRepository.save(role).getId();
    }

    public void delete(UUID id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("El rol no existe");
        }
        roleRepository.deleteById(id);
    }
}
