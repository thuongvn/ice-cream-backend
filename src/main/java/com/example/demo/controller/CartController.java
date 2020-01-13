package com.example.demo.controller;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;
import com.example.demo.model.detail.ProductDTO;
import com.example.demo.model.request.CreateCartItem;
import com.example.demo.model.request.CreateProduct;
import com.example.demo.service.CartItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cartitem")
@Api(value = "Cart itemm APIs")
public class CartController {
    @Autowired
    CartItemService cartItemService;

    @ApiOperation(value = "create a cart", response = CartItem.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody @Valid CreateCartItem createCartItem) {
        CartItem cartItem = cartItemService.createCartItem(createCartItem);
        if(cartItem!=null){
            return ResponseEntity.ok(cartItem);
        }else{
            return ResponseEntity.ok("create product faill");
        }

    }
}
