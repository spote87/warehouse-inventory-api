package com.example.inventory.service;

import com.example.inventory.model.ArticleRequest;

/**
 * Article service interface.
 *
 * @author Shivaji Pote
 **/
public interface ArticleService {

  /**
   * This method loads article data in database.
   *
   * @param articleRequest {@link ArticleRequest} containing articles data
   */
  void saveArticles(ArticleRequest articleRequest);

}
