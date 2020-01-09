package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import java.util.List;

@Component
public class ProductImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
//    @Autowired
//    private EntityManager entityManager;

    @Override
    public Product createProduct(Product product) {
        if(product.getProduct_name()!=null && product.getProduct_name() != "") {
            productRepository.save(product);
            return product;
        }else{
            return null;
        }
    }

    @Override
    public Product updateProduct(Product product, int id) {
        Product products = productRepository.findById(id).get();
        if(product.getProduct_name()!=null && product.getProduct_name() != ""){

            products.setProduct_name(product.getProduct_name());
            products.setDescription(product.getDescription());
            products.setCartItems(product.getCartItems());
            products.setCreate_at(product.getCreate_at());
            products.setUpdate_at(product.getUpdate_at());
            products.setImages(product.getImages());
            products.setPrice(product.getPrice());
            productRepository.save(products);
        }
        return productRepository.findById(id).get();
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

    @Override
    public List<Product> getProducstByFreeText(String keyword) {
//        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//
//        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
//                .buildQueryBuilder()
//                .forEntity(Product.class)
//                .get();
//
//        Query query = queryBuilder
//                .keyword()
//                .onField("product_name")
//                .matching(keyword)
//                .createQuery();
//        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Product.class);
//        List<Product> results = jpaQuery.getResultList();
        return null;
    }

}
