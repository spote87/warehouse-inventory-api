package com.example.inventory.service;

import com.example.inventory.entity.ArticleEntity;
import com.example.inventory.model.Article;
import com.example.inventory.model.ArticleRequest;
import com.example.inventory.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author Shivaji Pote
 **/
@Log4j2
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

  private final ArticleRepository articleRepository;

  @Override
  public void saveArticles(final ArticleRequest articleRequest) {
    log.debug("Saving articles data in database");
    final Iterable<? extends ArticleEntity> articles = articleRequest.getArticle().stream().map(this::getArticleEntities).collect(Collectors.toList());
    articleRepository.saveAll(articles);
  }

  private ArticleEntity getArticleEntities(final Article article) {
    return new ArticleEntity(article.getArticleId(), article.getName(), article.getStock());
  }

}
