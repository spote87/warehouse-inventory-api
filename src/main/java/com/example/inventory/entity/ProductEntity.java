package com.example.inventory.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
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

//  @ManyToMany(cascade = CascadeType.REMOVE)
//  @JoinTable(name = "PRODUCT_ARTICLE", joinColumns = @JoinColumn(name = "PRODUCT_ID"), inverseJoinColumns = @JoinColumn(name = "ARTICLE_ID"))
//  private Set<ArticleEntity> articleEntities = new HashSet<>();
}
