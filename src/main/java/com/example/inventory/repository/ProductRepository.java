package com.example.inventory.repository;

import com.example.inventory.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Shivaji Pote
 **/
@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
