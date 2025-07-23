/*
package com.mudaya.mudaya.seeders;

import com.mudaya.mudaya.data.repositories.RoleRepository;
import com.mudaya.mudaya.domain.entities.Role;
import com.mudaya.mudaya.domain.enums.UserRol;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RoleSeeder {

    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void seedRoles() {
        for (UserRol rol : UserRol.values()) {
            roleRepository.findByName(rol).orElseGet(() -> {
                Role role = new Role(UUID.randomUUID(), rol);
                return roleRepository.save(role);
            });
        }
        System.out.println("Roles insertados si no existían.");
    }
}
*/
