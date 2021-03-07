package com.example.inventory.service;

import com.example.inventory.entity.ArticleEntity;
import com.example.inventory.model.Article;

/**
 * @author Shivaji Pote
 **/
public final class InventoryHelper {

  private InventoryHelper() throws InstantiationException {
    throw new InstantiationException("Cannot instantiate this class");
  }

  public static Article getArticle(final ArticleEntity articleEntity) {
    final Article.ArticleBuilder builder = Article.builder();
    builder.articleId(articleEntity.getArticleId());
    builder.stock(articleEntity.getAvailableStock());
    builder.name(articleEntity.getName());
    return builder.build();
  }


}
