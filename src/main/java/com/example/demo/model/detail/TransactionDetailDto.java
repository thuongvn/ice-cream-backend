package com.example.demo.model.detail;

import lombok.Data;

import java.util.Date;
@Data
public class TransactionDetailDto {
    private int id;
    private int product_id;
    private String product_name;
    private int quantity;
    private float total_money;
    private Date create_at;
    private Date update_at;
    private int transaction_id;
}
