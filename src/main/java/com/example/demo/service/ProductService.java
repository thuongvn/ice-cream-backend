package com.example.demo.service;

import com.example.demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public Product createProduct(Product product);
    public Product updateProduct(Product product, int id);
    public boolean deleteProduct(int id);
    public List<Product> getAllProduct();
    public Product getProductById(int id);
    public List<Product> getProducstByFreeText(String keyword);
}
