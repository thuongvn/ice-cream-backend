package com.example.demo.service;

import com.example.demo.entity.Store;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreService {
    public Store createStore(Store store);
    public Store updateStore(Store store, int id);
    public boolean deleteStore(int id);
    public List<Store> getAllStore();
    public Store getStoreById(int id);
}
