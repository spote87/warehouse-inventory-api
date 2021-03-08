package com.example.inventory.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * This is factory class to determine the strategy to be executed.
 *
 * @author Shivaji Pote
 **/
@Log4j2
@RequiredArgsConstructor
@Component
public class DataLoaderStrategyFactory {

  private final ApplicationContext context;

  /**
   * This method returns {@link ProductLoaderStrategy} or {@link ArticleLoaderStrategy} based on name of the file. If
   * file name is invalid, it reuturns null.
   *
   * @param fileName file name
   * @return {@link DataLoaderStrategy} pointing to instance of either {@link ArticleLoaderStrategy} or {@link
   * ProductLoaderStrategy}. It returns null of file name is invalid.
   */
  public DataLoaderStrategy getStrategy(final String fileName) {
    final DataLoaderStrategy strategy;
    switch (fileName) {
      case "products":
        strategy = context.getBean(ProductLoaderStrategy.class);
        break;
      case "inventory":
        strategy = context.getBean(ArticleLoaderStrategy.class);
        break;
      default:
        strategy = null;
    }
    return strategy;
  }
}
