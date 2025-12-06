package fr.adriencaubel.demo_rest_api;

import fr.adriencaubel.demo_rest_api.entite.Article;
import fr.adriencaubel.demo_rest_api.entite.Commande;
import fr.adriencaubel.demo_rest_api.entite.LigneCommande;
import fr.adriencaubel.demo_rest_api.exception.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CommandeTest {
    private Article createArticle(int stock, BigDecimal prix) {
        Article article = new Article();
        article.setStock(stock);
        article.setPrix(prix);
        return article;
    }

    private Commande createEmptyCommande() {
        Commande commande = new Commande();
        commande.setLigneCommandes(new ArrayList<>());
        return commande;
    }

    @Test
    public void addLigneToCommand_valid() {
        // Given
        Article article = createArticle(10, new BigDecimal("100.00"));
        Commande commande = createEmptyCommande();

        // Then
        commande.addLigneToCommande(article, 2);

        // Then
        Assertions.assertEquals(1, commande.getLigneCommandes().size());
        LigneCommande ligneCommande = commande.getLigneCommandes().get(0);
        Assertions.assertEquals(article, ligneCommande.getArticle());
        Assertions.assertEquals(2, ligneCommande.getQuantite());
        Assertions.assertEquals(new BigDecimal("100.00"), ligneCommande.getPrixUnitaire());
    }

    @Test
    public void addLigneToCommand_withNegativeOrZeroQuantite() {
        // Given
        Article article = createArticle(10, new BigDecimal("100.00"));
        Commande commande = createEmptyCommande();

        // When
        BusinessException exception = Assertions.assertThrows(
                BusinessException.class,
                () -> commande.addLigneToCommande(article, 0));

        // Assert
        Assertions.assertEquals("La quantite doit être supérieure ou égale à 1", exception.getMessage());
    }
}
