package com.example.inventory.strategy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

/**
 * @author Shivaji Pote
 **/
@ExtendWith(MockitoExtension.class)
class DataLoaderStrategyFactoryTest {

  @InjectMocks
  private DataLoaderStrategyFactory strategyFactory; //= new DataLoaderStrategyFactory();

  @Mock
  private ApplicationContext context;

  private final DataLoaderStrategy productLoaderStrategy = Mockito.mock(ProductLoaderStrategy.class);

  private final DataLoaderStrategy articleLoaderStrategy = Mockito.mock(ArticleLoaderStrategy.class);

  @Test
  void getStrategy_ReturnsProductsStrategyWhenFileNameIsProducts() {
    doReturn(productLoaderStrategy).when(context).getBean(ProductLoaderStrategy.class);
    final DataLoaderStrategy strategy = strategyFactory.getStrategy("products");
    assertNotNull(strategy);
    assertTrue(strategy instanceof ProductLoaderStrategy);
  }

  @Test
  void getStrategy_ReturnsArticleStrategyWhenFileNameIsInventory() {
    doReturn(articleLoaderStrategy).when(context).getBean(ArticleLoaderStrategy.class);
    final DataLoaderStrategy strategy = strategyFactory.getStrategy("inventory");
    assertNotNull(strategy);
    assertTrue(strategy instanceof ArticleLoaderStrategy);
  }

  @Test
  void getStrategy_ReturnsNullWhenInvalidFileNameIsPassed() {
    assertNull(strategyFactory.getStrategy("invalid-file"));
  }

}
