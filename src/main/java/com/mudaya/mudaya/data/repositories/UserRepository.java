package com.mudaya.mudaya.data.repositories;

import com.mudaya.mudaya.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByDni(String dni);
    Optional<User> findByPhoneNumber(String phoneNumber);
}
