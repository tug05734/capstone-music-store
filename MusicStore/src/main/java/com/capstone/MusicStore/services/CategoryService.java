package com.capstone.MusicStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.MusicStore.entities.Category;
import com.capstone.MusicStore.repositories.CategoryRepository;

@Service
public class CategoryService {
	
 @Autowired
 CategoryRepository categoryRepository;
 
 public Category getCategory(int categoryId) {
	 return categoryRepository.findById(categoryId).orElse(null);
 }
 
 public Iterable<Category> GetAllCategories() {
		return categoryRepository.findAll();
	}
 
 public Category saveCategory(Category category) {
	 return categoryRepository.save(category);
 }
 
 public void deleteCategory(int id) {
	 categoryRepository.delete(getCategory(id));
 }
}
