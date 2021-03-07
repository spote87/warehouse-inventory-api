package com.example.inventory.repository;

import com.example.inventory.entity.ArticleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Shivaji Pote
 **/
@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {
}
