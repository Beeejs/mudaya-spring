package com.mudaya.mudaya.domain.managers;

import com.mudaya.mudaya.domain.entities.Role;
import com.mudaya.mudaya.domain.entities.User;
import com.mudaya.mudaya.data.repositories.UserRepository;
import com.mudaya.mudaya.domain.enums.UserRol;
import com.mudaya.mudaya.utils.PasswordUtils;
import com.mudaya.mudaya.utils.RoleUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SessionManager {

    private final UserRepository userRepository;
    private final PasswordUtils passwordUtils;
    private final RoleUtils roleUtils;

    // Mantener la sesión en memoria (opcional si no usás Spring Security)
    private User currentUser;

    public SessionManager(UserRepository userRepository, PasswordUtils passwordUtils, RoleUtils roleUtils) {
        this.userRepository = userRepository;
        this.passwordUtils = passwordUtils;
        this.roleUtils = roleUtils;
    }

    public User login(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("No se encontró el usuario");
        }

        User existingUser = optionalUser.get();

        if (!passwordUtils.verifyPassword(user.getPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        this.currentUser = existingUser;
        return existingUser;
    }

    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        Role staffRole = roleUtils.getEstablishedRole(UserRol.valueOf("STAFF"));

        String hashedPassword = passwordUtils.hashPassword(user.getPassword());

        User newUser = new User(
                UUID.randomUUID(),
                user.getEmail(),
                hashedPassword,
                staffRole,
                user.getName(),
                user.getSurname(),
                user.getPhoneNumber(),
                user.getDni(),
                user.getSexo()
        );

        userRepository.save(newUser);
        newUser.setPassword(null); // ocultamos la password

        this.currentUser = newUser;
        return newUser;
    }

    public User current() {
        return this.currentUser;
    }

    public void logout() {
        this.currentUser = null;
    }
}
