package com.example.inventory.strategy;

import com.example.inventory.model.ArticleRequest;
import com.example.inventory.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Shivaji Pote
 **/
@ExtendWith(MockitoExtension.class)
class ArticleLoaderStrategyTest {

  @InjectMocks
  private ArticleLoaderStrategy dataLoaderStrategy;

  @Mock
  private ArticleService articleService;

  @Test
  void load_CallsArticleServiceMethodsToLoadDataFromProvidedDataStream() throws IOException {
    dataLoaderStrategy.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("inventory.json"));
    verify(articleService, times(1)).saveArticles(any(ArticleRequest.class));
  }
}
