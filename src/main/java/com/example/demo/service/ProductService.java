package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.model.detail.ListProductDto;
import com.example.demo.model.detail.ProductDTO;
import com.example.demo.model.request.CreateProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public ProductDTO createProduct(CreateProduct product);
    public ProductDTO updateProduct(CreateProduct product, int id);
    public boolean deleteProduct(int id);
    public List<Product> getAllProduct();
    public Product getProductById(int id);
    public ListProductDto getAllProducst(String keyword, int page);
    public List<Product> getAllProducst();
}
