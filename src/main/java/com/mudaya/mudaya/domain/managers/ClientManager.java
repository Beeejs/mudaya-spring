package com.mudaya.mudaya.domain.managers;

import com.mudaya.mudaya.domain.entities.Client;
import com.mudaya.mudaya.data.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientManager {

    private final ClientRepository clientRepo;

    public ClientManager(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    public List<Client> getAll(String filter, Integer limit) {
        List<Client> result;

        if (filter != null && !filter.trim().isEmpty()) {
            result = clientRepo.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCaseOrDni(
                    filter, filter, filter
            );
        } else {
            result = clientRepo.findAll();
        }

        if (limit != null && limit > 0 && limit < result.size()) {
            return result.subList(0, limit);
        }

        return result;
    }

    public Client getOne(UUID id) {
        return clientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public Client create(Client client) {
        if (client.getId() == null) {
            client.setId(UUID.randomUUID());
        }
        return clientRepo.save(client);
    }

    public UUID update(Client client) {
        if (!clientRepo.existsById(client.getId())) {
            throw new RuntimeException("Cliente no existe");
        }
        return clientRepo.save(client).getId();
    }

    public void delete(Client client) {
        clientRepo.deleteById(client.getId());
    }
}
