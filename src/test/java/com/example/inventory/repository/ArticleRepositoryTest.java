package com.example.inventory.repository;

import com.example.inventory.entity.ArticleEntity;
import com.example.inventory.model.ArticleRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static com.example.inventory.utils.TestUtil.mockedArticleEntities;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Shivaji Pote
 **/
@SpringBootTest
class ArticleRepositoryTest {

  @Autowired
  private ArticleRepository articleRepository;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void saveAll_SavesAllArticlesIndatabase() throws IOException {
    final List<ArticleEntity> articleEntities = mockedArticleEntities(getArticleRequest());
    assertNotNull(articleEntities);
    assertEquals(4, articleEntities.size());
  }

  private ArticleRequest getArticleRequest() throws IOException {
    return objectMapper.readValue(Thread.currentThread().getContextClassLoader().getResourceAsStream("inventory.json"), ArticleRequest.class);
  }
}
