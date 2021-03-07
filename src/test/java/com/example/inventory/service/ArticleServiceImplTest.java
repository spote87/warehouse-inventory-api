package com.example.inventory.service;

import com.example.inventory.model.ArticleRequest;
import com.example.inventory.repository.ArticleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;

import static com.example.inventory.utils.TestUtil.mockedArticleEntity;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.*;

/**
 * @author Shivaji Pote
 **/
@ExtendWith(MockitoExtension.class)
class ArticleServiceImplTest {

  @InjectMocks
  private ArticleServiceImpl articleService;

  @Mock
  private ArticleRepository articleRepository;

  private final ObjectMapper mapper = new ObjectMapper();

  @Test
  void saveArticles_CallsRespectiveRepositoryMethodsToSaveArticles() throws IOException {
    when(articleRepository.saveAll(anyCollection())).thenReturn(Arrays.asList(mockedArticleEntity(1l)));
    articleService.saveArticles(getArticle());
    verify(articleRepository, times(1)).saveAll(anyCollection());
  }

  private ArticleRequest getArticle() throws IOException {
    return mapper.readValue(Thread.currentThread().getContextClassLoader().getResourceAsStream("inventory.json"), ArticleRequest.class);
  }
}
