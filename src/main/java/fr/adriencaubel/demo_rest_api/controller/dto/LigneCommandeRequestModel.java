package fr.adriencaubel.demo_rest_api.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LigneCommandeRequestModel {
    private Long articleId;
    private int quantite;
}
