package com.example.demo.model.detail;

import com.example.demo.entity.StoreHaveProduct;
import lombok.Data;

import java.util.List;
@Data
public class ListStoreHaveProduct {
    private List<StoreHaveProductDto> list;
    private int totalPages;
    private long totalItems;
}
