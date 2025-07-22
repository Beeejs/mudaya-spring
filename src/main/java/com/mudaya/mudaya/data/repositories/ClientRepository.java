package com.mudaya.mudaya.data.repositories;

import com.mudaya.mudaya.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    List<Client> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCaseOrDni(String name, String surname, String dni);
}
