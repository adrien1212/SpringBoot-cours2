package fr.adriencaubel.demo_rest_api.service;

import fr.adriencaubel.demo_rest_api.entite.Article;
import fr.adriencaubel.demo_rest_api.exception.BusinessException;
import fr.adriencaubel.demo_rest_api.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new BusinessException("Article not found"));
    }
}
