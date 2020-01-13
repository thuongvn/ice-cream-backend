package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.model.detail.ListProductDto;
import com.example.demo.model.detail.ListUserDto;
import com.example.demo.model.detail.ProductDTO;
import com.example.demo.model.detail.UserDto;
import com.example.demo.model.mapper.ProductMapper;
import com.example.demo.model.mapper.UserMapper;
import com.example.demo.model.request.CreateProduct;
import com.example.demo.repository.ProductRepository;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ProductImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
//    @Autowired
//    private EntityManager entityManager;

    @Override
    public ProductDTO createProduct(CreateProduct createProduct) {
        try{
            Product product = new Product();
            product.setProduct_name(createProduct.getProduct_name());
            product.setDescription(createProduct.getDescription());
            product.setImages(createProduct.getImages());
            product.setPrice(createProduct.getPrice());
            product.setCreate_at(new Date(System.currentTimeMillis()));
            productRepository.save(product);
            return ProductMapper.toProductDTO(product);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public ProductDTO updateProduct(CreateProduct product, int id) {
        try{
            Product products = productRepository.findById(id).get();


            products.setProduct_name(product.getProduct_name());
            products.setDescription(product.getDescription());
            products.setUpdate_at(new Date(System.currentTimeMillis()));
            products.setImages(product.getImages());
            products.setPrice(product.getPrice());

            productRepository.save(products);
            return ProductMapper.toProductDTO(products);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteProduct(int id) {
        try {
            productRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product getProductById(int id) {
        Product product = productRepository.findById(id).get();
        return product;
    }

    //for web
    @Override
    public ListProductDto getAllProducst(String keyword, int page){

        // Phân trang + sắp xếp
        if(keyword==null){
            keyword="";
        }
        Page<Product> rs = productRepository.searchProduct(keyword, PageRequest.of(page,5));
        List<Product> products = rs.getContent();

        ListProductDto list = new ListProductDto();
        list.setTotalItems(rs.getTotalElements());
        list.setTotalPages(rs.getTotalPages());
        list.setList(products);
        return list;
    }

    //for front-end
    @Override
    public List<Product> getAllProducst() {
        return productRepository.findAll();
    }

}
