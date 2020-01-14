package com.example.demo.service;

import com.example.demo.entity.Store;
import com.example.demo.entity.StoreHaveProduct;
import com.example.demo.model.detail.ListStoreHaveProduct;
import com.example.demo.model.request.UpdateQuantityOfProduct;
import com.example.demo.repository.StoreHaveProductRepository;
import com.example.demo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class StoreHaveProductServiceImpl implements StoreHaveProductService {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreHaveProductRepository storeHaveProductRepository;

    @Override
    public StoreHaveProduct createStoreHaveProduct(StoreHaveProduct storeHaveProduct, int id) {
        Store store = storeRepository.findById(id).get();
        StoreHaveProduct storeHaveProducts = new StoreHaveProduct();

        storeHaveProducts.setStore(store);
        storeHaveProducts.setProduct(storeHaveProduct.getProduct());
        storeHaveProducts.setQuantity(storeHaveProduct.getQuantity());
        storeHaveProducts.setStatus(storeHaveProduct.getStatus());
        try {
            return storeHaveProductRepository.save(storeHaveProducts);
        }catch (Exception e){
            return null;
        }


    }

    @Override
    public StoreHaveProduct updateStoreHaveProduct(UpdateQuantityOfProduct storeHaveProduct) {
        StoreHaveProduct store_product = storeHaveProductRepository.findById(storeHaveProduct.getId()).get();
        store_product.setQuantity(storeHaveProduct.getQuantity());
        try {
            return storeHaveProductRepository.save(store_product);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public ListStoreHaveProduct getAllStoreHaveProduct(int store_id, int page) {
        try {
            Page<StoreHaveProduct> rs = storeHaveProductRepository.findStoreHaveProductByStore(store_id, PageRequest.of(page-1,5));
            List<StoreHaveProduct> storeHaveProductsContent= rs.getContent();
            ListStoreHaveProduct list = new ListStoreHaveProduct();
            list.setList(storeHaveProductsContent);
            list.setTotalItems(rs.getTotalElements());
            list.setTotalPages(rs.getTotalPages());
            return list;
        }catch (Exception e){
            return null;
        }
    }
}
