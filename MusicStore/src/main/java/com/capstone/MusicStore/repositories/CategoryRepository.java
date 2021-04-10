package com.capstone.MusicStore.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capstone.MusicStore.entities.Category;
import com.capstone.MusicStore.entities.Product;

public interface CategoryRepository extends CrudRepository<Category, Integer>{
	
	@Query(value = "SELECT c FROM Category c WHERE c.name = ?1")
	Category findCategoryByName(String name);
		
}
