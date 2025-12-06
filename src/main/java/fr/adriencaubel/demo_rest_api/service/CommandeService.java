package fr.adriencaubel.demo_rest_api.service;

import fr.adriencaubel.demo_rest_api.controller.dto.CommandeRequestModel;
import fr.adriencaubel.demo_rest_api.controller.dto.LigneCommandeRequestModel;
import fr.adriencaubel.demo_rest_api.entite.Article;
import fr.adriencaubel.demo_rest_api.entite.Client;
import fr.adriencaubel.demo_rest_api.entite.Commande;
import fr.adriencaubel.demo_rest_api.exception.BusinessException;
import fr.adriencaubel.demo_rest_api.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ArticleService articleService;

    public Commande findById(Long id) {
        return commandeRepository.findById(id).orElseThrow(() -> new BusinessException("Commande not found"));
    }

    @Transactional
    public void create(CommandeRequestModel commandeRequestModel) {
        Commande commande = new Commande();

        Client client = clientService.findById(commandeRequestModel.getClientId());
        commande.setClient(client);
        commande.setLigneCommandes(new ArrayList<>());

        commandeRepository.save(commande);
    }

    @Transactional
    public void addLigne(Long commandeId, LigneCommandeRequestModel ligneCommandeRequestModel) {
        Commande commande = this.findById(commandeId);
        Article article = articleService.findById(ligneCommandeRequestModel.getArticleId());

        article.reserveStock(ligneCommandeRequestModel.getQuantite());
        commande.addLigneToCommande(article, ligneCommandeRequestModel.getQuantite());

        commandeRepository.save(commande);
    }

    @Transactional
    public void validateCommande(Long commandeId) {
        Commande commande = this.findById(commandeId);
        commande.validateCommande();
    }
}