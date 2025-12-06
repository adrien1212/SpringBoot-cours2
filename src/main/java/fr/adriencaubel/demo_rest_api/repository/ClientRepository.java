package fr.adriencaubel.demo_rest_api.repository;

import fr.adriencaubel.demo_rest_api.entite.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
