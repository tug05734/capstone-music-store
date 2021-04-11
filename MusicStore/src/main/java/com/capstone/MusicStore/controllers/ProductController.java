package com.capstone.MusicStore.controllers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.capstone.MusicStore.entities.Category;
import com.capstone.MusicStore.entities.Genre;
import com.capstone.MusicStore.entities.Product;
import com.capstone.MusicStore.entities.User;
import com.capstone.MusicStore.models.CartSaveModel;
import com.capstone.MusicStore.services.CategoryService;
import com.capstone.MusicStore.services.GenreService;
import com.capstone.MusicStore.services.ProductService;
import com.capstone.MusicStore.services.UserService;
import com.capstone.MusicStore.controllers.CartController;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private GenreService genreService;
	@Autowired
	private UserService userService;
	@Autowired
	private CartController cartController;
	
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
		if(keyword.isEmpty()) {
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
		mav.getModelMap().addAttribute("productID", product.get().getProductID());
		return mav;
	}
	
	@GetMapping("/edit/details/{id}")
	public ModelAndView showAdminProductDetails(@PathVariable("id") int id) {
		ModelAndView mav= new ModelAndView();
		mav.setViewName("product/product_admin_details_view");
		Iterable<Category> categories = categoryService.GetAllCategories();
		Iterable<Genre> genres = genreService.GetAllGenre();
		Optional<Product> product = productService.FindRepositoryById(id);
		mav.getModelMap().addAttribute("categories", categories);
		mav.getModelMap().addAttribute("genres" ,genres);
		mav.getModelMap().addAttribute("currentGenre", product.get().getGenre().getId());
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
																		@RequestParam(name = "price")String price,
																		@RequestParam(name = "image")String imgPath,
																		@RequestParam(name = "category")int category,
																		@RequestParam(name = "genre")int genre) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product/product_details_view");
		Optional<Product> product = productService.FindRepositoryById(id);
		Product newProduct = product.get();
		newProduct.setProductName(productName);
		newProduct.setCondition(condition);
		newProduct.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
		newProduct.setImagePath(imgPath);
		newProduct.setCategory(categoryService.getCategory(category));
		newProduct.setGenre(genreService.getGenre(genre));
		productService.AddProduct(newProduct);
		mav.getModelMap().addAttribute("name", newProduct.getProductName());
		mav.getModelMap().addAttribute("condition", newProduct.getCondition());
		mav.getModelMap().addAttribute("price", newProduct.getPrice());
		mav.getModelMap().addAttribute("image", newProduct.getImagePath());
		mav.getModelMap().addAttribute("category", newProduct.getCategory().getName());
		mav.getModelMap().addAttribute("genre", newProduct.getGenre().getName());
		mav.getModelMap().addAttribute("productID", newProduct.getProductID());
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
	
	
	//*****This method will only work if @RequestBody is removed from saveCartItem(CartSaveModel cartSaveModel) method in CartController
	@PostMapping("/addCart/{id}")
	public ModelAndView addToCart(@PathVariable("id")int id, @RequestParam(name="quantity")int quantity) {
		
		CartSaveModel cartModel = new CartSaveModel();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		//User user = userService.findUserById(1);
		Optional<Product> product = productService.FindRepositoryById(id);
		cartModel.setUserId(user.getId());
		cartModel.setProductId(product.get().getProductID());
		cartModel.setPrice(product.get().getPrice().multiply(BigDecimal.valueOf(quantity)));
		cartModel.setQuantity(quantity);
		cartController.saveCartItem(cartModel);
		return showProductDetails(id);
	}
	
	
}
