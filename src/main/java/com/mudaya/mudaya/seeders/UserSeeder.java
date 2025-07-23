/*
package com.mudaya.mudaya.seeders;

import com.mudaya.mudaya.domain.entities.Role;
import com.mudaya.mudaya.domain.entities.User;
import com.mudaya.mudaya.domain.enums.Sexo;
import com.mudaya.mudaya.domain.enums.UserRol;
import com.mudaya.mudaya.domain.managers.RoleManager;
import com.mudaya.mudaya.domain.managers.UserManager;
import com.mudaya.mudaya.utils.PasswordUtils;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserSeeder {

    private final UserManager userManager;
    private final RoleManager roleManager;
    private final Dotenv dotenv = Dotenv.load();

    public UserSeeder(UserManager userManager, RoleManager roleManager) {
        this.userManager = userManager;
        this.roleManager = roleManager;
    }

    @PostConstruct
    public void seedUser() {
        try {
            if (!adminExists()) {
                Role adminRole = roleManager.getOneByField("name", UserRol.ADMIN.name());
                if (adminRole == null) {
                    System.err.println("No se encontró el rol ADMIN.");
                    return;
                }

                User adminUser = new User(
                        UUID.randomUUID(),
                        dotenv.get("ADMIN_EMAIL"),
                        PasswordUtils.hashPassword(dotenv.get("ADMIN_PASSWORD")),
                        adminRole,
                        dotenv.get("ADMIN_NAME"),
                        dotenv.get("ADMIN_SURNAME"),
                        dotenv.get("ADMIN_PHONE"),
                        dotenv.get("ADMIN_DNI"),
                        Sexo.M
                );

                userManager.create(adminUser);
                System.out.println("✅ Usuario ADMIN creado.");
            } else {
                System.out.println("ℹ️ Ya existe un usuario con rol ADMIN.");
            }
        } catch (Exception e) {
            System.err.println("❌ Error al crear el usuario ADMIN: " + e.getMessage());
        }
    }

    private boolean adminExists() {
        return userManager.existsAdmin();
    }
}
*/
