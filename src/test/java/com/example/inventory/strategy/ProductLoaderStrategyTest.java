package com.example.inventory.strategy;

import com.example.inventory.model.ProductRequest;
import com.example.inventory.service.ProductService;
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
class ProductLoaderStrategyTest {

  @InjectMocks
  private ProductLoaderStrategy productLoaderStrategy;

  @Mock
  private ProductService productService;

  @Test
  void load_CalssServiceMethodsToLoadProductsDataFromProvidedDataStream() throws IOException {
    productLoaderStrategy.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("products.json"));
    verify(productService, times(1)).saveProducts(any(ProductRequest.class));
  }
}
