package com.example.demo.repository;

import com.example.demo.entity.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem,Long> {
}
