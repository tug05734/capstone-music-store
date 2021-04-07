package com.capstone.MusicStore.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.capstone.MusicStore.entities.Product;
import com.capstone.MusicStore.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/all", method= RequestMethod.GET)
	public ModelAndView showAllProducts() {
		ModelAndView mav = new ModelAndView();
		Iterable<Product> products = productService.GetAllMusic();
		mav.setViewName("product/productview");
		mav.getModelMap().addAttribute("products", products);
		return mav;
		
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public ModelAndView showSearchedProducts(@RequestParam(name = "search")String keyword) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product/productview");
		Iterable<Product> products;
		if(keyword.isBlank()) {
			products = productService.GetAllMusic();
		}else {
			products = productService.SearchProductByKeyword(keyword);
		}
		mav.getModelMap().addAttribute("products", products);
		
		
		return mav;
	}
	
	@GetMapping("/details/{id}")
	public ModelAndView showProductDetails(@PathVariable("id") int id) {
		ModelAndView mav= new ModelAndView();
		mav.setViewName("product/product_details_view");
		Optional<Product> product = productService.FindRepositoryById(id);
		mav.getModelMap().addAttribute("name", product.get().getProductName());
		mav.getModelMap().addAttribute("condition", product.get().getCondition());
		mav.getModelMap().addAttribute("price", product.get().getPrice());
		mav.getModelMap().addAttribute("image", product.get().getImagePath());
		return mav;
	}
	
}
