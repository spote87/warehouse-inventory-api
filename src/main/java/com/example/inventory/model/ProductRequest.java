package com.example.inventory.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Shivaji Pote
 **/
@Getter
@Setter
public class ProductRequest {

  private List<Product> products;

}
