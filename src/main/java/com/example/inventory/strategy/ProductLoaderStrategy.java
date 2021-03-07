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
 * @author Shivaji Pote
 **/
@Lazy
@Log4j2
@RequiredArgsConstructor
@Component
public class ProductLoaderStrategy implements DataLoaderStrategy {

  private final ProductService productService;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void load(final InputStream data) throws IOException {
    log.info("Loading products data");
    final ProductRequest productRequest = objectMapper.readValue(data, ProductRequest.class);
    productService.saveProducts(productRequest);
  }
}
