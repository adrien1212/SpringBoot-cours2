package fr.adriencaubel.demo_rest_api.controller;

import fr.adriencaubel.demo_rest_api.controller.dto.ClientResponseModel;
import fr.adriencaubel.demo_rest_api.entite.Client;
import fr.adriencaubel.demo_rest_api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RequestMapping("/clients")
@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<ClientResponseModel> getClients() {
        List<Client> clients = clientService.findAll();

        List<ClientResponseModel> clientResponseModels = new ArrayList<>();
        for (Client client : clients) {
            clientResponseModels.add(new ClientResponseModel(client.getId(), client.getNom(), client.getPrenom(), client.getAdresse()));
        }
        return clientResponseModels;
    }

    @GetMapping("{id}")
    public ClientResponseModel getClient(@PathVariable Long id) {
        Client client = clientService.findById(id);
        return new ClientResponseModel(client.getId(), client.getNom(), client.getPrenom(), client.getAdresse());
    }
}
