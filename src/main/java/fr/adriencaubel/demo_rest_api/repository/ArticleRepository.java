package fr.adriencaubel.demo_rest_api.repository;

import fr.adriencaubel.demo_rest_api.entite.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
