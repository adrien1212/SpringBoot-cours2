package fr.adriencaubel.demo_rest_api.controller.dto;

import java.math.BigDecimal;

public record ArticleResponseModel(Long id, String libelle, String description, double poids, BigDecimal prix) {}

