package com.example.inventory.strategy;

import com.example.inventory.model.ArticleRequest;
import com.example.inventory.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * Strategy implementation for articles.
 *
 * @author Shivaji Pote
 **/
@Lazy
@Log4j2
@RequiredArgsConstructor
@Component
public class ArticleLoaderStrategy implements DataLoaderStrategy {

  private final ArticleService articleService;

  private final ObjectMapper objectMapper = new ObjectMapper();

  /**
   * This method loads articles data in warehouse inventory database.
   *
   * @param data {@link InputStream} containing article data to be loaded in database
   * @throws IOException if fails to read articles data
   */
  @Override
  public void load(final InputStream data) throws IOException {
    log.info("Loading articles data");
    final ArticleRequest articleRequest = objectMapper.readValue(data, ArticleRequest.class);
    articleService.saveArticles(articleRequest);
  }
}
