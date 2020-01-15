package com.example.demo.model.detail;

import lombok.Data;

@Data
public class StoreHaveProductDto {
    private int store_id;
    private String store_name;
    private int quantity;
    private int product_id;
    private String product_name;
}
