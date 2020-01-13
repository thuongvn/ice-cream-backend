package com.example.demo.service;

import com.example.demo.entity.Store;
import com.example.demo.entity.User;
import com.example.demo.model.request.CreateStore;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Store createStore(CreateStore createStore) {

        try{
            Store store = new Store();
            store.setName(createStore.getName());
            store.setLocation(createStore.getLocation());
            store.setAddres(createStore.getAddress());
            User user = userRepository.findByEmail(createStore.getEmail());

            if(user!=null){
                store.setUser(user);
                return  storeRepository.save(store);
            }else{
                return null;
            }
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public Store updateStore(CreateStore createStore, int id) {
        try {
            Store stores = storeRepository.findById(id).get();
            stores.setAddres(createStore.getAddress());
            stores.setLocation(createStore.getLocation());
            stores.setName(createStore.getName());
            User user = userRepository.findByEmail(createStore.getEmail());

            if(user!=null){
                stores.setUser(user);
                return  storeRepository.save(stores);
            }else{
                return null;
            }
        }catch (Exception e){
            return null;
        }


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
