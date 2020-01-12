package com.example.demo.service;


import com.example.demo.entity.StoreHaveProduct;
import com.example.demo.model.request.UpdateQuantityOfProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreHaveProductService {
    public StoreHaveProduct createStoreHaveProduct(StoreHaveProduct storeHaveProduct, int shop);
    public StoreHaveProduct updateStoreHaveProduct(UpdateQuantityOfProduct updateQuantityOfProduct);
    public List<StoreHaveProduct> getAllStoreHaveProduct();
}
