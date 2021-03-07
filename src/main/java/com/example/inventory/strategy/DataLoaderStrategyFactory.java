package com.example.inventory.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Shivaji Pote
 **/
@Log4j2
@RequiredArgsConstructor
@Component
public class DataLoaderStrategyFactory {

  private final ApplicationContext context;

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
