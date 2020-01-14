package com.example.demo.model.detail;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionDto {
    private int id;
    private Date create_at;
    private Date update_at;
    private float total_price;
    private String address;
    private int status;
}
