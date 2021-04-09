package com.capstone.MusicStore.controllers;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@RequestMapping(value="/admin/search", method=RequestMethod.POST)
	public ModelAndView showAdminSearchedProducts(@RequestParam(name = "search")String keyword) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product/product_admin_view");
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
		mav.getModelMap().addAttribute("category", product.get().getCategory().getName());
		mav.getModelMap().addAttribute("genre", product.get().getGenre().getName());
		mav.getModelMap().addAttribute("condition", product.get().getCondition());
		mav.getModelMap().addAttribute("price", product.get().getPrice());
		mav.getModelMap().addAttribute("image", product.get().getImagePath());
		return mav;
	}
	
	@GetMapping("/edit/details/{id}")
	public ModelAndView showAdminProductDetails(@PathVariable("id") int id) {
		ModelAndView mav= new ModelAndView();
		mav.setViewName("product/product_admin_details_view");
		Optional<Product> product = productService.FindRepositoryById(id);
		mav.getModelMap().addAttribute("name", product.get().getProductName());
		mav.getModelMap().addAttribute("condition", product.get().getCondition());
		mav.getModelMap().addAttribute("price", product.get().getPrice());
		mav.getModelMap().addAttribute("image", product.get().getImagePath());
		mav.getModelMap().addAttribute("productID", product.get().getProductID());
		return mav;
	}
	
	@PostMapping("/details/{id}")
	public ModelAndView editProductDetails(@PathVariable("id") int id, @RequestParam(name = "name")String productName,
																		@RequestParam(name = "condition")String condition,
																		@RequestParam(name = "price")String price) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product/product_details_view");
		Optional<Product> product = productService.FindRepositoryById(id);
		Product newProduct = product.get();
		newProduct.setProductName(productName);
		newProduct.setCondition(condition);
		newProduct.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
		newProduct.setImagePath(product.get().getImagePath());
		productService.AddProduct(newProduct);
		mav.getModelMap().addAttribute("name", newProduct.getProductName());
		mav.getModelMap().addAttribute("condition", newProduct.getCondition());
		mav.getModelMap().addAttribute("price", newProduct.getPrice());
		mav.getModelMap().addAttribute("image", newProduct.getImagePath());
		return mav;
	}
	
	@PostMapping("/delete/{id}")
	public ModelAndView deleteProduct(@PathVariable("id") int id) {
		//ModelAndView mav = new ModelAndView();
		Optional<Product> product = productService.FindRepositoryById(id);
		productService.DeleteProduct(product.get());
		//mav.setViewName("product/product_admin_view");
		return showAdminAllProducts();
	}
	
	@GetMapping("/admin/all")
	public ModelAndView showAdminAllProducts() {
		ModelAndView mav = new ModelAndView();
		Iterable<Product> products = productService.GetAllMusic();
		mav.setViewName("product/product_admin_view");
		mav.getModelMap().addAttribute("products", products);
		return mav;
	}
	
	@GetMapping("/admin/add")
	public ModelAndView showAddProduct() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product/product_add");
		return mav;
	}
	
	@PostMapping("/admin/add")
	public ModelAndView addProduct(@RequestParam(name="name")String productName, @RequestParam(name="condition")String condition,
																				@RequestParam(name="price")String price,
																				@RequestParam(name="imgPath")String imagePath) {
		
		Product product = new Product();
		product.setProductName(productName);
		product.setCondition(condition);
		product.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
		product.setImagePath(imagePath);
		productService.AddProduct(product);
		
		return showAdminAllProducts();
	}
}
