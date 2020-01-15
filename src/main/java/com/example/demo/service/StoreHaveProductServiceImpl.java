package com.example.demo.service;

import com.example.demo.entity.Store;
import com.example.demo.entity.StoreHaveProduct;
import com.example.demo.model.detail.ListStoreHaveProduct;
import com.example.demo.model.detail.StoreHaveProductDto;
import com.example.demo.model.mapper.StoreHaveProductMapper;
import com.example.demo.model.request.UpdateQuantityOfProduct;
import com.example.demo.repository.StoreHaveProductRepository;
import com.example.demo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class StoreHaveProductServiceImpl implements StoreHaveProductService {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreHaveProductRepository storeHaveProductRepository;

    @Override
    public StoreHaveProductDto createStoreHaveProduct(StoreHaveProduct storeHaveProduct, int id) {
        Store store = storeRepository.findById(id).get();
        StoreHaveProduct storeHaveProducts = new StoreHaveProduct();

        storeHaveProducts.setStore(store);
        storeHaveProducts.setProduct(storeHaveProduct.getProduct());
        storeHaveProducts.setQuantity(storeHaveProduct.getQuantity());
        storeHaveProducts.setStatus(storeHaveProduct.getStatus());
        try {
            return StoreHaveProductMapper.toDto(storeHaveProductRepository.save(storeHaveProducts));
        }catch (Exception e){
            return null;
        }


    }

    @Override
    public StoreHaveProductDto updateStoreHaveProduct(UpdateQuantityOfProduct storeHaveProduct) {
        StoreHaveProduct store_product = storeHaveProductRepository.findById(storeHaveProduct.getId()).get();
        store_product.setQuantity(storeHaveProduct.getQuantity());
        try {
            return StoreHaveProductMapper.toDto(storeHaveProductRepository.save(store_product));
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public ListStoreHaveProduct getAllStoreHaveProduct(int store_id, int page) {
        try {
            Page<StoreHaveProduct> rs = storeHaveProductRepository.findStoreHaveProductByStore(store_id, PageRequest.of(page,5));
            List<StoreHaveProduct> storeHaveProductsContent= rs.getContent();
            ListStoreHaveProduct list = new ListStoreHaveProduct();
            List<StoreHaveProductDto> storeHaveProductDtos = new ArrayList<>();
            for(StoreHaveProduct s : storeHaveProductsContent){
                storeHaveProductDtos.add(StoreHaveProductMapper.toDto(s));
            }
            list.setList(storeHaveProductDtos);
            list.setTotalItems(rs.getTotalElements());
            list.setTotalPages(rs.getTotalPages());
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
