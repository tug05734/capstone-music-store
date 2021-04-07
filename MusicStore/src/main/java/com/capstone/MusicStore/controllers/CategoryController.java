package com.capstone.MusicStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.MusicStore.entities.Category;
import com.capstone.MusicStore.services.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/category")
	@CrossOrigin
	public @ResponseBody Iterable<Category> getAllCategories() {
        // This returns a JSON or XML with the Feedbacks
		System.out.println("Get hit");
        return categoryService.GetAllCategories();
    }
	
	@PostMapping("/category")
	@CrossOrigin
	public Category saveCategory(@RequestBody Category category) {
		System.out.println("Post hit");
		return categoryService.saveCategory(category);
	}
	
	@PostMapping("/deleteCategory")
	@CrossOrigin
	public void deleteCategory(@RequestBody int categoryId) {
		System.out.println("Post hit");
	    categoryService.deleteCategory(categoryId);;
	}

}
