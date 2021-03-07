package com.example.inventory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author Shivaji Pote
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Article {

  @JsonProperty("art_id")
  private long articleId;

  private String name;

  private int stock;

}
