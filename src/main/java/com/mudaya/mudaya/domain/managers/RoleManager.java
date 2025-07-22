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

    private final RoleRepository roleRepo;

    public RoleManager(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    public List<Role> getAll() {
        return roleRepo.findAll();
    }

    public Role getOneByField(String field, String value) {
        return switch (field) {
            case "id" -> roleRepo.findById(UUID.fromString(value))
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado por id"));
            case "name" -> roleRepo.findByName(UserRol.valueOf(value.toUpperCase()))
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado por nombre"));
            default -> throw new IllegalArgumentException("Campo inválido para búsqueda de rol");
        };
    }

    public Role create(Role role) {
        if (role.getId() == null) {
            role.setId(UUID.randomUUID());
        }
        return roleRepo.save(role);
    }

    public UUID update(Role role) {
        if (!roleRepo.existsById(role.getId())) {
            throw new RuntimeException("El rol no existe");
        }
        return roleRepo.save(role).getId();
    }

    public void delete(Role role) {
        roleRepo.deleteById(role.getId());
    }
}
