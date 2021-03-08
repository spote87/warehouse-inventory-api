package com.example.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO class holding products data.
 *
 * @author Shivaji Pote
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO implements Serializable {

  private static final long serialVersionUID = -3568866434714118039L;

  private String name;

  private long productId;

  private List<Article> articles;

  private BigDecimal price;
}
