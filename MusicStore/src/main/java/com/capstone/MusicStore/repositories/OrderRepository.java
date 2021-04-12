package com.capstone.MusicStore.repositories;

import org.springframework.data.repository.CrudRepository;

import com.capstone.MusicStore.entities.Orders;

public interface OrderRepository extends CrudRepository<Orders, Integer> {
  Iterable<Orders> findByUserId(int userId);
}
