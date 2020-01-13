package com.example.demo.repository;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartItemRepository extends CrudRepository<CartItem,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM car WHERE ?1")
    public List<CartItem> getAllByUser(int id_user);
}
