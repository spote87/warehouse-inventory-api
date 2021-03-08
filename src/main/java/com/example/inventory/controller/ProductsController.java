package com.example.inventory.controller;

import com.example.inventory.model.ProductDTO;
import com.example.inventory.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This controller contains endpoints to manage products.
 *
 * @author Shivaji Pote
 **/
@Log4j2
@RequiredArgsConstructor
@RestController
public class ProductsController {

  private final ProductService productService;

  /**
   * This endpoint returns available products in warehouse with it's articles and it's stock.
   *
   * @return list of {@link ProductDTO}
   */
  @GetMapping(value = "products", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ProductDTO>> getAllProducts() {
    log.debug("Retrieving all products");
    return ResponseEntity.ok(productService.getAllProducts());
  }

  /**
   * This method removes product from inventory and adjusts inventory stock accordingly.
   *
   * @param productId id of the product which needs to be removed
   * @return success message
   */
  @DeleteMapping(value = "product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> sellProduct(@PathVariable final long productId) {
    log.debug("Selling product {}", productId);
    productService.saleProduct(productId);
    return ResponseEntity.ok("Product sold!!");
  }
}
