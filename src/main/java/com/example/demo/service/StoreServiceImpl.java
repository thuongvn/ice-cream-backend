package com.example.demo.service;

import com.example.demo.entity.Store;
import com.example.demo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;
    @Override
    public Store createStore(Store store) {
        Store stores = storeRepository.save(store);
        return stores;
    }

    @Override
    public Store updateStore(Store store, int id) {
        Store stores = storeRepository.findById(id).get();
        stores.setAddres(store.getAddres());
        stores.setLocation(store.getLocation());
        stores.setName(store.getName());

        return storeRepository.save(stores);
    }

    @Override
    public boolean deleteStore(int id) {
        try {
            storeRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }


    }

    @Override
    public List<Store> getAllStore() {
        return storeRepository.findAll();
    }

    @Override
    public Store getStoreById(int id) {
        return storeRepository.findById(id).get();
    }
}
