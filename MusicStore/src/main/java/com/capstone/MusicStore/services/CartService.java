package com.capstone.MusicStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.MusicStore.entities.Cart;
import com.capstone.MusicStore.models.CartSaveModel;
import com.capstone.MusicStore.repositories.CartRepository;

@Service
public class CartService {
	
	 @Autowired
	 CartRepository cartRepository;
	 
	 @Autowired
	 UserService userService;
	 
	 @Autowired
	 ProductService productService;
	 
	 public Iterable<Cart> getCartItemsByUserId(int userId){
		 return cartRepository.findByUserId(userId);
	 }
	 
	 public Iterable<Cart> getCartItemsByUser(int userId){
		 return cartRepository.findByUser(userService.findUserById(userId));
	 }
	 
	 public void deleteCartItem(int cartId){
		 cartRepository.deleteById(cartId);
	 }
	 
	 public Cart saveCartItem(CartSaveModel cartSaveModel){
		 Cart cart = new Cart(
				 userService.findUserById(cartSaveModel.getUserId())
				,productService.FindRepositoryById(cartSaveModel.getProductId()).orElse(null)
				,cartSaveModel.getQuantity()
				,cartSaveModel.getPrice()
				 );
		 return cartRepository.save(cart);
	 }

}
