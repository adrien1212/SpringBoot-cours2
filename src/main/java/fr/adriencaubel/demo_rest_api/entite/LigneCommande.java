package fr.adriencaubel.demo_rest_api.entite;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "t_ligne_commande")
public class LigneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Article article;

    @Column
    private Integer quantite;

    @ManyToOne
    private Commande commande;

    @Column
    private BigDecimal prixUnitaire;
}
