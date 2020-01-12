package com.example.demo.model.detail;

import com.example.demo.entity.Product;
import lombok.Data;

import java.util.List;
@Data
public class ListProductDto {
    private List<Product> list;
    private int totalPages;
    private long totalItems;
}
