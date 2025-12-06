package fr.adriencaubel.demo_rest_api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientResponseModel {
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
}
