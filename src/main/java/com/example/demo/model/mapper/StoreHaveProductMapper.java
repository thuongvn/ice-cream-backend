package com.example.demo.model.mapper;

import com.example.demo.entity.Store;
import com.example.demo.entity.StoreHaveProduct;
import com.example.demo.model.detail.StoreHaveProductDto;

public class StoreHaveProductMapper {
    public static StoreHaveProductDto toDto(StoreHaveProduct store){
        StoreHaveProductDto storeHaveProductDto = new StoreHaveProductDto();
        storeHaveProductDto.setProduct_id(store.getProduct().getId());
        storeHaveProductDto.setProduct_name(store.getProduct().getProduct_name());
        storeHaveProductDto.setQuantity(store.getQuantity());
        storeHaveProductDto.setStore_id(store.getStore().getId());
        storeHaveProductDto.setStore_name(store.getStore().getName());
return storeHaveProductDto;
    }
}
