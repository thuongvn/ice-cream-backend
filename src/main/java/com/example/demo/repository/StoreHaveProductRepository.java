package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.StoreHaveProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreHaveProductRepository extends JpaRepository<StoreHaveProduct,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM store_have_product")
    public Page<StoreHaveProduct> findStoreHaveProductByStore(int id, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM store_have_product WHERE store_id = ?1")
    public List<StoreHaveProduct> findStoreHaveProduct(int store_id);
}
