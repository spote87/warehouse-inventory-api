package com.example.inventory.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Shivaji Pote
 **/
@Getter
@Setter
@Entity
@Table(name = "PRODUCT_ARTICLE")
public class ProductArticleEntity implements Serializable {

  private static final long serialVersionUID = 2458277470592186214L;

  @EmbeddedId
  private ProductArticleKey productArticleKey;

  @Column(name = "QUANTITY", nullable = false)
  private int quantity;

  @Getter
  @Setter
  @Embeddable
  public static class ProductArticleKey implements Serializable {

    private static final long serialVersionUID = 3148693026204761244L;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID", nullable = false)
    private ArticleEntity articleEntity;
  }

}
