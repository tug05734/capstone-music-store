package com.capstone.MusicStore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.MusicStore.entities.Cart;
import com.capstone.MusicStore.entities.Orders;
import com.capstone.MusicStore.repositories.CartRepository;
import com.capstone.MusicStore.repositories.OrderRepository;

@Service
public class OrderService {
	
@Autowired
OrderRepository orderRepository;

@Autowired
CartRepository cartRepository;

public Orders saveOrder(Orders order) {
	Orders savedOrder = orderRepository.save(order);
    List<Cart> cartItems = savedOrder.getCart();
    for(Cart cartItem: cartItems) {
    	cartItem.setStatus("Complete");
    	cartRepository.save(cartItem);
    }
    return savedOrder;
}

public Iterable<Orders> getOrdersByUserId(int userId){
	return orderRepository.findByUserId(userId);
}
}
