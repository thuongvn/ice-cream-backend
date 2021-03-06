package com.example.demo.service;


import com.example.demo.entity.StoreHaveProduct;
import com.example.demo.model.detail.ListStoreHaveProduct;
import com.example.demo.model.detail.StoreHaveProductDto;
import com.example.demo.model.request.UpdateQuantityOfProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreHaveProductService {
    public StoreHaveProductDto createStoreHaveProduct(StoreHaveProduct storeHaveProduct, int shop);
    public StoreHaveProductDto updateStoreHaveProduct(UpdateQuantityOfProduct updateQuantityOfProduct);
    public ListStoreHaveProduct getAllStoreHaveProduct(int store_id, int page);
}
