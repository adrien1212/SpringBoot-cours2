package fr.adriencaubel.demo_rest_api;

import fr.adriencaubel.demo_rest_api.controller.dto.LigneCommandeRequestModel;
import fr.adriencaubel.demo_rest_api.entite.Article;
import fr.adriencaubel.demo_rest_api.entite.Commande;
import fr.adriencaubel.demo_rest_api.entite.LigneCommande;
import fr.adriencaubel.demo_rest_api.repository.CommandeRepository;
import fr.adriencaubel.demo_rest_api.service.ArticleService;
import fr.adriencaubel.demo_rest_api.service.CommandeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CommandeServiceTest {

    @Mock
    private CommandeRepository commandeRepository;

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private CommandeService commandeService;

    private Commande commande;
    private Article article;
    private LigneCommandeRequestModel requestModel;

    @BeforeEach
    void setUp() {
        commande = new Commande();
        commande.setId(1L);
        commande.setLigneCommandes(new ArrayList<>());

        article = new Article();
        article.setId(1L);
        article.setStock(10);
        article.setPrix(new BigDecimal("100.00"));

        requestModel = new LigneCommandeRequestModel();
        requestModel.setArticleId(1L);
        requestModel.setQuantite(5);
    }

    @Test
    void addLigne_shouldAddLigneSuccessfully_whenValidInput() {
        // Given
        when(commandeRepository.findById(1L)).thenReturn(Optional.of(commande));
        when(articleService.findById(1L)).thenReturn(article);

        // When
        commandeService.addLigne(1L, requestModel);

        // Then
        assertEquals(5, article.getStock());
        assertEquals(1, commande.getLigneCommandes().size());

        LigneCommande addedLigne = commande.getLigneCommandes().get(0);
        assertEquals(article, addedLigne.getArticle());
        assertEquals(5, addedLigne.getQuantite());
        assertEquals(article.getPrix(), addedLigne.getPrixUnitaire());

        verify(commandeRepository, times(1)).save(commande);
    }
}