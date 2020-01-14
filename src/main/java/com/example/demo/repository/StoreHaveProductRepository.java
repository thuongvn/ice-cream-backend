package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.StoreHaveProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StoreHaveProductRepository extends JpaRepository<StoreHaveProduct,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM store_store_have_products WHERE product_name LIKE %?1%")
    public Page<StoreHaveProduct> searchProduct(int id, Pageable pageable);
}
