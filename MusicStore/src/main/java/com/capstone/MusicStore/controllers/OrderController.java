package com.capstone.MusicStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.capstone.MusicStore.entities.Orders;
import com.capstone.MusicStore.services.OrderService;

@RestController
public class OrderController {

	@Autowired 
	OrderService orderService;
	
	@PostMapping("/order")
	@CrossOrigin
	public Orders saveOrder(@RequestBody Orders order) {
		return orderService.saveOrder(order);
	}
	
	@PostMapping("/orderByUserId")
	@CrossOrigin
	public Iterable<Orders> getOrderByUserId(@RequestBody int userId) {
		return orderService.getOrdersByUserId(userId);
	}
	
	@RequestMapping(value="/orderView", method= RequestMethod.GET)
	public ModelAndView getOrderView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("order/order");
		return mav;
	}
	
	@RequestMapping(value="/confirmationView", method= RequestMethod.GET)
	public ModelAndView getConfirmationView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("order/confirmation");
		return mav;
	}
}
