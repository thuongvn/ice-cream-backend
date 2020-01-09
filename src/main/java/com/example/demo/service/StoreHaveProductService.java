package com.example.demo.service;


import com.example.demo.entity.StoreHaveProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreHaveProductService {
    public StoreHaveProduct createStoreHaveProduct(StoreHaveProduct storeHaveProduct, int shop);
    public StoreHaveProduct updateStoreHaveProduct(StoreHaveProduct storeHaveProduct, int id);
    public List<StoreHaveProduct> getAllStoreHaveProduct();
}
