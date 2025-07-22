package com.mudaya.mudaya.data.repositories;

import com.mudaya.mudaya.domain.entities.Role;
import com.mudaya.mudaya.domain.enums.UserRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    // Buscar por el nombre del rol
    Optional<Role> findByName(UserRol name);
    Optional<Role> findById(UUID id);
}
