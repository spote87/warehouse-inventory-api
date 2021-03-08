package com.example.inventory.strategy;

import com.example.inventory.model.ProductRequest;
import com.example.inventory.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * Products data loader strategy implementation.
 *
 * @author Shivaji Pote
 **/
@Lazy
@Log4j2
@RequiredArgsConstructor
@Component
public class ProductLoaderStrategy implements DataLoaderStrategy {

  private final ProductService productService;

  private final ObjectMapper objectMapper = new ObjectMapper();

  /**
   * This method loads products data in database.
   *
   * @param data {@link InputStream} containing produts data to be loaded in database
   * @throws IOException if fails to read products data from passed input stream
   */
  @Override
  public void load(final InputStream data) throws IOException {
    log.info("Loading products data");
    final ProductRequest productRequest = objectMapper.readValue(data, ProductRequest.class);
    productService.saveProducts(productRequest);
  }
}
