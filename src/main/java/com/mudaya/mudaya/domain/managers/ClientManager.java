package com.mudaya.mudaya.domain.managers;

import com.mudaya.mudaya.domain.entities.Client;
import com.mudaya.mudaya.data.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientManager {

    private final ClientRepository clientRepository;

    public ClientManager(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll(String filter, Integer limit) {
        List<Client> result;

        if (filter != null && !filter.trim().isEmpty()) {
            result = clientRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCaseOrDni(
                    filter, filter, filter
            );
        } else {
            result = clientRepository.findAll();
        }

        if (limit != null && limit > 0 && limit < result.size()) {
            return result.subList(0, limit);
        }

        return result;
    }

    public Client getOne(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public UUID update(Client client) {
        if (!clientRepository.existsById(client.getId())) {
            throw new RuntimeException("Cliente no existe");
        }
        return clientRepository.save(client).getId();
    }

    public void delete(UUID id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Cliente no existe");
        }
        clientRepository.deleteById(id);
    }
}
