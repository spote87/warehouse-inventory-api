package com.example.inventory.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Shivaji Pote
 **/
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "ARTICLE")
public class ArticleEntity implements Serializable {

  private static final long serialVersionUID = 3569281677577169884L;

  @Id
  @Column(name = "ARTICLE_ID", nullable = false)
  private long articleId;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "AVAILABLE_STOCK", nullable = false)
  private int availableStock;

  public ArticleEntity(final long articleId, final String name, final int availableStock) {
    this.articleId = articleId;
    this.name = name;
    this.availableStock = availableStock;
  }

}
