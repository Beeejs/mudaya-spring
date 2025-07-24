package com.mudaya.mudaya.data.repositories;

import com.mudaya.mudaya.domain.entities.User;
import com.mudaya.mudaya.domain.enums.UserRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByDni(String dni);
    Optional<User> findByPhoneNumber(String phoneNumber);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.role.name = :roleName")
    boolean existsByRoleName(@Param("roleName") UserRol role);
}
