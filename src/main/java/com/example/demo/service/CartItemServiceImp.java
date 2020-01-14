package com.example.demo.service;

import com.example.demo.entity.CartItem;
import com.example.demo.model.detail.CartItemDto;
import com.example.demo.model.request.CreateCartItem;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    public CartItemDto createCartItem(CreateCartItem createCartItem) {
        try {
            if(createCartItem.getQuantity()>0){
                CartItem cartItem = new CartItem();
                cartItem.setUser(userRepository.findByEmail(createCartItem.getEmail()));
                cartItem.setProduct(productRepository.findById(createCartItem.getProduct()).get());
                cartItem.setQuantity(createCartItem.getQuantity());
                cartItemRepository.save(cartItem);
                CartItemDto cartItemDto = new CartItemDto();
                cartItemDto.setId(cartItem.getId());
                cartItemDto.setProduct_id(cartItem.getProduct().getId());
                cartItemDto.setProduct_name(cartItem.getProduct().getProduct_name());
                cartItemDto.setQuantity(cartItem.getQuantity());

                return cartItemDto;
            }
            return null;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

    @Override
    public CartItemDto updateCartItem(int id, int quantity) {
        try {
            if(quantity>0){
                CartItem cartItem = cartItemRepository.findById(id).get();
                cartItem.setQuantity(quantity);
                cartItemRepository.save(cartItem);

                CartItemDto cartItemDto = new CartItemDto();
                cartItemDto.setId(cartItem.getId());
                cartItemDto.setProduct_id(cartItem.getProduct().getId());
                cartItemDto.setProduct_name(cartItem.getProduct().getProduct_name());
                cartItemDto.setQuantity(cartItem.getQuantity());
                return cartItemDto;
            }else{
                cartItemRepository.deleteById(id);
            }
            return null;

        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<CartItemDto> getAllCartItem(int id_user) {
        try {
            List<CartItem> list = cartItemRepository.getAllByUser(id_user);
            List<CartItemDto> cartItemDtoList = new ArrayList<>();
            for(CartItem cartItem : list){
                CartItemDto cartItemDto = new CartItemDto();
                cartItemDto.setId(cartItem.getId());
                cartItemDto.setProduct_id(cartItem.getProduct().getId());
                cartItemDto.setProduct_name(cartItem.getProduct().getProduct_name());
                cartItemDto.setQuantity(cartItem.getQuantity());
                cartItemDtoList.add(cartItemDto);
            }
            return cartItemDtoList;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public List<CartItem> getAllltest() {
        return (List<CartItem>) cartItemRepository.findAll();
    }
}
