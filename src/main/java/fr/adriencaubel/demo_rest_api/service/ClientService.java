package fr.adriencaubel.demo_rest_api.service;

import fr.adriencaubel.demo_rest_api.entite.Client;
import fr.adriencaubel.demo_rest_api.exception.BusinessException;
import fr.adriencaubel.demo_rest_api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new BusinessException("Client not found"));
    }
}
