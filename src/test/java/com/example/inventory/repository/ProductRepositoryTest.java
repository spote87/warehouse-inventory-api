package com.example.inventory.repository;

import com.example.inventory.entity.ArticleEntity;
import com.example.inventory.entity.ProductEntity;
import com.example.inventory.model.ArticleRequest;
import com.example.inventory.model.ProductArticle;
import com.example.inventory.model.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.inventory.utils.TestUtil.mockedArticleEntities;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Shivaji Pote
 **/
@DataJpaTest
class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ArticleRepository articleRepository;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  public void setup() throws IOException {
    articleRepository.saveAll(mockedArticleEntities(getArticleRequest()));
  }

  @Test
  public void saveAll_SavesProductsData() throws IOException {
    final Iterable<ProductEntity> productEntities = productRepository.saveAll(mockedProductEntities(getProductRequest()));
    assertNotNull(productEntities);
  }

  private List<ProductEntity> mockedProductEntities(final ProductRequest productRequest) {
    return productRequest.getProducts().stream().map(this::getProductEntity).collect(Collectors.toList());
  }

  private ProductEntity getProductEntity(final com.example.inventory.model.Product product) {
    final ProductEntity entity = new ProductEntity();
    entity.setName(product.getName());
    final Set<ArticleEntity> articleEntities = new HashSet<>();
    for (final ProductArticle article : product.getArticles()) {
      articleEntities.add(getArticleEntity(article));
    }
//    entity.setArticleEntities(articleEntities);
    return entity;
  }

  private ArticleEntity getArticleEntity(final ProductArticle article) {
    return articleRepository.findById(article.getArticleId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Data"));
  }

  private ProductRequest getProductRequest() throws IOException {
    return objectMapper.readValue(Thread.currentThread().getContextClassLoader().getResourceAsStream("products.json"), ProductRequest.class);
  }


  private ArticleRequest getArticleRequest() throws IOException {
    return objectMapper.readValue(Thread.currentThread().getContextClassLoader().getResourceAsStream("inventory.json"), ArticleRequest.class);
  }

}

