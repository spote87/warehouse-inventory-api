package com.example.inventory.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * This is entity class for <em>PRODUCT</em> table.
 *
 * @author Shivaji Pote
 **/
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "PRODUCT")
public class ProductEntity implements Serializable {

  private static final long serialVersionUID = 3069723337879815880L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PRODUCT_ID", nullable = false)
  private Long productId;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "PRICE")
  private BigDecimal price;

  @OneToMany(mappedBy = "productArticleKey.productEntity", cascade = CascadeType.ALL)
  private Set<ProductArticleEntity> productArticleEntity;

}
