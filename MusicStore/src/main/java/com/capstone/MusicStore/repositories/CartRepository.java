package com.capstone.MusicStore.repositories;

import org.springframework.data.repository.CrudRepository;

import com.capstone.MusicStore.entities.Cart;
import com.capstone.MusicStore.entities.User;

public interface CartRepository extends CrudRepository<Cart, Integer> {
    public Iterable<Cart> findByUser(User user);
    
    public Iterable<Cart> findByUserId(int userId);
    
    public Iterable<Cart> findByUserIdAndStatus(int userId, String status);
}
