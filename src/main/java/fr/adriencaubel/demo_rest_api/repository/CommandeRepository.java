package fr.adriencaubel.demo_rest_api.repository;

import fr.adriencaubel.demo_rest_api.entite.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
