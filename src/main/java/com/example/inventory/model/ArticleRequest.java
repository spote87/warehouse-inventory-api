package com.example.inventory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Shivaji Pote
 **/
@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ArticleRequest {

  @JsonProperty("inventory")
  private List<Article> article;

}
