package com.example.inventory.service;

import com.example.inventory.entity.ArticleEntity;
import com.example.inventory.model.Article;

/**
 * Utility class containing helper methods for warehouse inventory API application.
 *
 * @author Shivaji Pote
 **/
public final class InventoryHelper {

  /**
   * Do not let anyone create instance of this class as this is meant for utility methods.
   *
   * @throws InstantiationException
   */
  private InventoryHelper() throws InstantiationException {
    throw new InstantiationException("Cannot instantiate this class");
  }

  /**
   * This method convert article entiry to {@link Article}.
   *
   * @param articleEntity article entity
   * @return {@link Article} instance
   */
  public static Article getArticle(final ArticleEntity articleEntity) {
    final Article.ArticleBuilder builder = Article.builder();
    builder.articleId(articleEntity.getArticleId());
    builder.stock(articleEntity.getAvailableStock());
    builder.name(articleEntity.getName());
    return builder.build();
  }


}
