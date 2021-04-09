package com.capstone.MusicStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.MusicStore.entities.Cart;
import com.capstone.MusicStore.models.CartSaveModel;
import com.capstone.MusicStore.services.CartService;


@RestController
public class CartController {

	@Autowired
	CartService cartService;
	
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
}
