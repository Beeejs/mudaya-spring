package com.mudaya.mudaya.seeders;

import com.mudaya.mudaya.domain.entities.Role;
import com.mudaya.mudaya.domain.enums.UserRol;
import com.mudaya.mudaya.domain.managers.RoleManager;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class RoleSeeder {

    private final RoleManager roleManager;

    public RoleSeeder(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    @PostConstruct
    public void seedRoles() {
        for (UserRol rol : UserRol.values()) {
            try {
                roleManager.getOneByField("name", rol.name());
                System.out.println("Rol " + rol + " ya existe.");
            } catch (RuntimeException e) {
                Role newRole = new Role(rol); // Ya no pasamos ID
                roleManager.create(newRole);
                System.out.println("Rol " + rol + " insertado.");
            }
        }
    }
}