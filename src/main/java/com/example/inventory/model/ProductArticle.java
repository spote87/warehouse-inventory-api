package com.example.inventory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Model class holding product article data.
 *
 * @author Shivaji Pote
 **/
@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ProductArticle {

  @JsonProperty("art_id")
  private long articleId;

  @JsonProperty("amount_of")
  private int quantity;

}
