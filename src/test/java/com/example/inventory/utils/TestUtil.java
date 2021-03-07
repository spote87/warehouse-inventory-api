package com.example.inventory.utils;

import com.example.inventory.entity.ArticleEntity;
import com.example.inventory.entity.ProductArticleEntity;
import com.example.inventory.entity.ProductEntity;
import com.example.inventory.model.ArticleRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Shivaji Pote
 **/
public final class TestUtil {


  public static ArticleEntity mockedArticleEntity(final long id) {
    return new ArticleEntity(id, "Test " + id, 10);
  }

  public static List<ArticleEntity> mockedArticleEntities(final ArticleRequest articleRequest) {
    return articleRequest.getArticle().stream().map(article -> new ArticleEntity(article.getArticleId(), article.getName(), article.getStock())).collect(Collectors.toList());
  }

  public static Set<ArticleEntity> mockedArticleEntities() {
    final Set<ArticleEntity> articles = new HashSet<>();
    final ArticleEntity article1 = new ArticleEntity(1, "test1", 5);
    articles.add(article1);
    final ArticleEntity article2 = new ArticleEntity(2, "test2", 8);
    articles.add(article2);
    return articles;
  }

  public static Iterable<ProductEntity> mockedProductEntities() {
    final List<ProductEntity> products = new ArrayList<>();
    final ProductEntity productEntity = new ProductEntity();
    productEntity.setProductId(1l);
    productEntity.setProductArticleEntity(mockedProductArticleEntities());
//    productEntity.setArticleEntities(mockedArticleEntities());
    products.add(productEntity);
    return products;
  }

  private static Set<ProductArticleEntity> mockedProductArticleEntities() {
    final Set<ProductArticleEntity> prdArticles = new HashSet<>();
    final ProductArticleEntity prdArt1 = new ProductArticleEntity();
    final ProductArticleEntity.ProductArticleKey prdArtKey1 = new ProductArticleEntity.ProductArticleKey();
    prdArtKey1.setProductEntity(mockedProductEntity(1));
    prdArtKey1.setArticleEntity(mockedArticleEntity(1));
    prdArt1.setProductArticleKey(prdArtKey1);
    prdArt1.setQuantity(3);
    prdArticles.add(prdArt1);
    final ProductArticleEntity prdArt2 = new ProductArticleEntity();
    final ProductArticleEntity.ProductArticleKey prdArtKey2 = new ProductArticleEntity.ProductArticleKey();
    prdArtKey2.setProductEntity(mockedProductEntity(1));
    prdArtKey2.setArticleEntity(mockedArticleEntity(2));
    prdArt2.setProductArticleKey(prdArtKey2);
    prdArt2.setQuantity(2);
    prdArticles.add(prdArt2);
    return prdArticles;
  }

  private static ProductEntity mockedProductEntity(final long id) {
    final ProductEntity entity = new ProductEntity();
    entity.setName("Test " + id);
    entity.setProductId(id);
    return entity;
  }
}
