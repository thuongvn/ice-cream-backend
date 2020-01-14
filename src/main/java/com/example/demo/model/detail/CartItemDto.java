package com.example.demo.model.detail;

import lombok.Data;

@Data
public class CartItemDto {
    private int id;
    private int product_id;
    private String product_name;
    private int quantity;
}
