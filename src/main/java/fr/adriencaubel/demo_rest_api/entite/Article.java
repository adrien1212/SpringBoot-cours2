package fr.adriencaubel.demo_rest_api.entite;

import fr.adriencaubel.demo_rest_api.exception.BusinessException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "t_article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String libelle;

    @Column
    private String description;

    @Column
    private double poids;

    @Column
    private BigDecimal prix;

    @Column
    private Integer stock;

    public void reserveStock(Integer quantite) {
        if(this.stock < quantite) {
            new BusinessException("Le stock est negative");
        }

        if(quantite <= 0) {
            new BusinessException("Le quantite est negative");
        }

        this.stock -= quantite;
    }
}
