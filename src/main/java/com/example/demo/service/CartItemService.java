package com.example.demo.service;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;
import com.example.demo.model.request.CreateCartItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartItemService {
    public CartItem createCartItem(CreateCartItem createCartItem);
    public CartItem updateCartItem(int id, int quantity);
    public List<CartItem> getAllCartItem(int id);
}
