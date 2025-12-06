package fr.adriencaubel.demo_rest_api.controller;

import fr.adriencaubel.demo_rest_api.controller.dto.CommandeRequestModel;
import fr.adriencaubel.demo_rest_api.controller.dto.LigneCommandeRequestModel;
import fr.adriencaubel.demo_rest_api.entite.LigneCommande;
import fr.adriencaubel.demo_rest_api.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/commandes")
@RestController
public class CommandeController {
    @Autowired
    private CommandeService commandeService;

    @PostMapping
    public void createCommande(@RequestBody CommandeRequestModel commandeRequestModel) {
        commandeService.create(commandeRequestModel);
    }

    @PostMapping("{id}/ligne")
    public void addLigneToCommande(@PathVariable Long id, @RequestBody LigneCommandeRequestModel ligneCommandeRequestModel) {
        commandeService.addLigne(id, ligneCommandeRequestModel);
    }

    @PostMapping("{id}/valide")
    public void valideCommande(@PathVariable Long id) {
        commandeService.validateCommande(id);
    }
}
