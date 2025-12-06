package fr.adriencaubel.demo_rest_api.entite;

import fr.adriencaubel.demo_rest_api.exception.BusinessException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "t_commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<LigneCommande> ligneCommandes;

    @Enumerated(EnumType.STRING)
    private StatutCommande statutCommande = StatutCommande.BROUILLON;

    @Column
    private BigDecimal prixTotal = BigDecimal.ZERO;

    public void addLigneToCommande(Article article, Integer quantite) {
        if(quantite <= 0) {
            throw new BusinessException("La quantite doit être supérieure ou égale à 1");
        }

        if(article.getStock() < quantite) {
            throw new BusinessException("Pas assez de stock");
        }

        LigneCommande ligneCommande = new LigneCommande();
        ligneCommande.setCommande(this);
        ligneCommande.setArticle(article);
        ligneCommande.setQuantite(quantite);
        ligneCommande.setPrixUnitaire(article.getPrix());

        ligneCommandes.add(ligneCommande);
    }

    public void validateCommande() {
        BigDecimal prixTotalCalcule = BigDecimal.ZERO;
        for(LigneCommande ligneCommande : ligneCommandes) {
            prixTotalCalcule = prixTotalCalcule.add(ligneCommande.getPrixUnitaire());
        }

        if(this.client.isVip()) {
            prixTotalCalcule = prixTotalCalcule.multiply(new BigDecimal("0.90"));
        }

        this.prixTotal = prixTotalCalcule;
        this.statutCommande = StatutCommande.COMMANDE;
    }
}