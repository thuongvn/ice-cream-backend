package com.example.demo.service;

import com.example.demo.entity.CartItem;
import com.example.demo.model.request.CreateCartItem;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartItemServiceImp implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public CartItem createCartItem(CreateCartItem createCartItem) {
        try {
            if(createCartItem.getQuantity()>0){
                CartItem cartItem = new CartItem();
                cartItem.setUser(userRepository.findByEmail(createCartItem.getEmail()));
                cartItem.setProduct(productRepository.findById(createCartItem.getProduct()).get());
                cartItem.setQuantity(createCartItem.getQuantity());
                return cartItemRepository.save(cartItem);
            }
            return null;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

    @Override
    public CartItem updateCartItem(int id, int quantity) {
        try {
            if(quantity>0){
                CartItem cartItem = cartItemRepository.findById(id).get();
                cartItem.setQuantity(quantity);
                return cartItemRepository.save(cartItem);
            }else{
                cartItemRepository.deleteById(id);
            }
            return null;

        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<CartItem> getAllCartItem(int id_user) {
        return  cartItemRepository.getAllByUser(id_user);
    }
}
