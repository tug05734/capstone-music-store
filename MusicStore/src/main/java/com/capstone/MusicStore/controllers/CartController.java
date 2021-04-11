package com.capstone.MusicStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.capstone.MusicStore.entities.Cart;
import com.capstone.MusicStore.entities.Product;
import com.capstone.MusicStore.entities.User;
import com.capstone.MusicStore.models.CartSaveModel;
import com.capstone.MusicStore.services.CartService;
import com.capstone.MusicStore.services.UserService;


@RestController
public class CartController {

	@Autowired
	CartService cartService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/getCartByUserId")
	@CrossOrigin
	public Iterable<Cart> getCartByUserId(@RequestBody int userId) {
		System.out.println("Post hit");
		return cartService.getCartItemsByUserIdAndStatus(userId, "New");
	}
	
	@PostMapping("/getCartByUser")
	@CrossOrigin
	public Iterable<Cart> getCartByUser(@RequestBody int userId) {
		System.out.println("Post hit");
		return cartService.getCartItemsByUser(userId);
	}
	
	@PostMapping("/deleteCartById")
	@CrossOrigin
	public void deleteCartById(@RequestBody int cartId) {
		cartService.deleteCartItem(cartId);
	}
	
	@PostMapping("/cart")
	@CrossOrigin
	public Cart saveCartItem(@RequestBody CartSaveModel cartSaveModel) {
		return cartService.saveCartItem(cartSaveModel);
	}
	
	@RequestMapping(value="/cartView", method= RequestMethod.GET)
	public ModelAndView getCartView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("cart/cart");
		return mav;
	}
	
	@PostMapping("/getCurrentUser")
	@CrossOrigin
	public User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		return user;
	}
}
