package fr.adriencaubel.demo_rest_api.controller;

import fr.adriencaubel.demo_rest_api.controller.dto.ArticleResponseModel;
import fr.adriencaubel.demo_rest_api.entite.Article;
import fr.adriencaubel.demo_rest_api.repository.ArticleRepository;
import fr.adriencaubel.demo_rest_api.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles")
    public List<ArticleResponseModel> getArticles() {
        List<Article> articles = articleService.findAll();

        List<ArticleResponseModel> articleResponseModels = new ArrayList<>();
        for(Article article : articles) {
            ArticleResponseModel articleResponseModel = new ArticleResponseModel(article.getId(), article.getLibelle(), article.getDescription(), article.getPoids(), article.getPrix());
            articleResponseModels.add(articleResponseModel);
        }

        return articleResponseModels;
    }

    @GetMapping("/articles/{id}")
    public ArticleResponseModel getArticle(@PathVariable Long id) {
        Article article = articleService.findById(id);

        return new ArticleResponseModel(article.getId(), article.getLibelle(), article.getDescription(), article.getPoids(), article.getPrix());
    }
}
