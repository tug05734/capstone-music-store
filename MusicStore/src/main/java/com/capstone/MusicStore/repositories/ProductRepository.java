package com.capstone.MusicStore.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capstone.MusicStore.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
	
	@Query(value = "SELECT p FROM Product p WHERE p.productName = ?1")
	Product findProductByName(String name);
	
	@Query("SELECT p FROM Product p WHERE p.price = ?1")
	Product findProductByPrice(BigDecimal price);
	
	
}
