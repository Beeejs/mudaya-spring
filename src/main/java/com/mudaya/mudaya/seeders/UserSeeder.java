package com.mudaya.mudaya.seeders;

import com.mudaya.mudaya.data.repositories.UserRepository;
import com.mudaya.mudaya.domain.entities.Role;
import com.mudaya.mudaya.domain.entities.User;
import com.mudaya.mudaya.domain.enums.Sexo;
import com.mudaya.mudaya.domain.enums.UserRol;
import com.mudaya.mudaya.domain.managers.RoleManager;
import com.mudaya.mudaya.domain.managers.UserManager;
import com.mudaya.mudaya.utils.PasswordUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder {

    private final RoleManager roleManager;
    private final PasswordUtils passwordUtils;
    private final UserRepository userRepository;

    // Datos del usuario admin inyectados desde application.properties
    @Value("${admin.email}")
    private String email;

    @Value("${admin.password}")
    private String password;

    @Value("${admin.name}")
    private String name;

    @Value("${admin.surname}")
    private String surname;

    @Value("${admin.phone}")
    private String phone;

    @Value("${admin.dni}")
    private String dni;

    @Value("${admin.sexo}")
    private String sexo; // "M" o "F"

    public UserSeeder(UserRepository userRepository, RoleManager roleManager, PasswordUtils passwordUtils) {
        this.userRepository = userRepository;
        this.roleManager = roleManager;
        this.passwordUtils = passwordUtils;
    }

    @PostConstruct
    public void seedAdminUser() {
        System.out.println("Intentando crear usuario admin...");
        try {
            if (userRepository.existsByRoleName(UserRol.ADMIN)) {
                System.out.println("Ya existe un usuario con rol ADMIN.");
                return;
            }

            Role adminRole = roleManager.getOneByField("name", UserRol.ADMIN.name());

            User user = new User(
                    name,
                    surname,
                    email,
                    passwordUtils.hashPassword(password),
                    phone,
                    dni,
                    Sexo.valueOf(sexo.toUpperCase())
            );
            user.setRole(adminRole);
            userRepository.save(user);

            System.out.println("Usuario ADMIN creado.");
        } catch (Exception e) {
            System.err.println("Error al crear el usuario ADMIN: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
