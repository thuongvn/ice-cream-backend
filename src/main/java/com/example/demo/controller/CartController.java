package com.example.demo.controller;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.Transaction;
import com.example.demo.model.detail.CartItemDto;
import com.example.demo.model.detail.ProductDTO;
import com.example.demo.model.detail.TransactionDto;
import com.example.demo.model.request.CreateCartItem;
import com.example.demo.model.request.CreateProduct;
import com.example.demo.model.request.UpdateQuantityOfProduct;
import com.example.demo.service.CartItemService;
import com.example.demo.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cartitem")
@Api(value = "Cart itemm APIs")
public class CartController {
    @Autowired
    CartItemService cartItemService;

    @Autowired
    OrderService orderService;
    @ApiOperation(value = "create a cart", response = CartItem.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PostMapping("")
    public ResponseEntity<?> createCart(@RequestBody @Valid CreateCartItem createCartItem) {

        CartItemDto cartItem = cartItemService.createCartItem(createCartItem);

        if (cartItem != null) {
            return ResponseEntity.ok(cartItem);
        } else {
            return ResponseEntity.ok("create product faill");
        }

    }

    @ApiOperation(value = "update a cart by cart id", response = CartItem.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PutMapping("")
    public ResponseEntity<?> updateCart(@RequestBody @Valid UpdateQuantityOfProduct updateQuantityOfProduct) {

        CartItemDto cartItem = cartItemService.updateCartItem(updateQuantityOfProduct.getId(), updateQuantityOfProduct.getQuantity());


        return ResponseEntity.ok(cartItem);


    }

    @ApiOperation(value = "get all cart item by author of user", response = CartItem.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("")
    public ResponseEntity<?> getCartByUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int id = (Integer) auth.getCredentials();
        List<CartItemDto> cartItems = cartItemService.getAllCartItem(id);

        return ResponseEntity.ok(cartItems);

    }

//    @GetMapping("/test")
//    public ResponseEntity<?> getAlltest() {
//
//        List<CartItem> cartItems = cartItemService.getAllltest();
//
//        return ResponseEntity.ok(cartItems);
//
//    }

    @ApiOperation(value = "create transaction for mobile", response = TransactionDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PostMapping("/transaction")
    public ResponseEntity<?> createTransaction(@RequestParam int product, @RequestParam int quantity) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int id = (Integer)auth.getCredentials();

        TransactionDto transactionDto = orderService.createTransactionMobile(id,quantity,product);

        if (transactionDto != null) {
            return ResponseEntity.ok(transactionDto);
        } else {
            return ResponseEntity.ok("create order faill");
        }

    }

    @ApiOperation(value = "update order by store", response = TransactionDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PutMapping("/transaction")
    public ResponseEntity<?> updateTransaction(@RequestParam int order_id, @RequestParam int status) {


        TransactionDto transactionDto = orderService.updateStatusOfOrder(order_id,status);

        if (transactionDto != null) {
            return ResponseEntity.ok(transactionDto);
        } else {
            return ResponseEntity.ok("update order faill");
        }

    }

    @ApiOperation(value = "get all product for store", response = CartItem.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("/transaction-store")
    public ResponseEntity<?> getAllTransactionForStore(@RequestParam(defaultValue = "1") int page) {

        return ResponseEntity.ok(orderService.getAllTransactionForStore(page));

    }

    @ApiOperation(value = "get all product for user", response = CartItem.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("/transaction-user")
    public ResponseEntity<?> getAllTransactionForUser(@RequestParam(defaultValue = "1") int page) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int id = (Integer) auth.getCredentials();
        return ResponseEntity.ok(orderService.getAllTransactionForUser(id,page));

    }
}
