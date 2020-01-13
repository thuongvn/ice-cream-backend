package com.example.demo.service;

import com.example.demo.entity.Store;
import com.example.demo.model.request.CreateStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreService {
    public Store createStore(CreateStore store);
    public Store updateStore(CreateStore store, int id);
    public boolean deleteStore(int id);
    public List<Store> getAllStore();
    public Store getStoreById(int id);
}
