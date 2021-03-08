package com.example.inventory.service;

import com.example.inventory.model.ProductDTO;
import com.example.inventory.model.ProductRequest;

import java.util.List;

/**
 * Product service interface.
 *
 * @author Shivaji Pote
 **/
public interface ProductService {

  /**
   * This mehtod loads products data in database.
   *
   * @param productRequest {@link ProductRequest} containing products data which need to be loaded in database.
   */
  void saveProducts(ProductRequest productRequest);

  /**
   * This method retrieves all products data from database.
   *
   * @return list of {@link ProductDTO}
   */
  List<ProductDTO> getAllProducts();

  /**
   * This method removes product with specified product id from database and adjusts inventory accordingly.
   *
   * @param productId id of the product which needs to be sold
   */
  void saleProduct(long productId);
}
