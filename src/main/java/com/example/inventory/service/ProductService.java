package com.example.inventory.service;

import com.example.inventory.model.ProductDTO;
import com.example.inventory.model.ProductRequest;

import java.util.List;

/**
 * @author Shivaji Pote
 **/
public interface ProductService {

  void saveProducts(ProductRequest productRequest);

  List<ProductDTO> getAllProducts();

  void saleProduct(long productId);
}
