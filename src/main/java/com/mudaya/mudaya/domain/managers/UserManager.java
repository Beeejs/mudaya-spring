package com.mudaya.mudaya.domain.managers;

import com.mudaya.mudaya.domain.entities.User;
import com.mudaya.mudaya.data.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserManager {

    private final UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getOneByField(String field, String value) {
        return switch (field.toLowerCase()) {
            case "email" -> userRepository.findByEmail(value)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            case "dni" -> userRepository.findByDni(value)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            case "phonenumber" -> userRepository.findByPhoneNumber(value)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            default -> throw new IllegalArgumentException("Campo no soportado: " + field);
        };
    }


    public User create(User user) {
        return userRepository.save(user);
    }

    public UUID update(User user) {
        if (!userRepository.existsById(user.getId())) {
            throw new RuntimeException("El usuario no existe");
        }
        userRepository.save(user);
        return user.getId();
    }

    public void delete(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("El usuario no existe");
        }
        userRepository.deleteById(id);
    }
}
