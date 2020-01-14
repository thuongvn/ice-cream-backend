package com.example.demo.service;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;
import com.example.demo.model.detail.CartItemDto;
import com.example.demo.model.request.CreateCartItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartItemService {
    public CartItemDto createCartItem(CreateCartItem createCartItem);
    public CartItemDto updateCartItem(int id, int quantity);
    public List<CartItemDto> getAllCartItem(int id);
    public List<CartItem> getAllltest();
}
