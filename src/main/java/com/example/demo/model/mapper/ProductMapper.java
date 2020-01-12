package com.example.demo.model.mapper;

import com.example.demo.entity.Product;
import com.example.demo.model.detail.ProductDTO;
import com.example.demo.model.request.CreateProduct;

public class ProductMapper {
    public static ProductDTO toProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProduct_name(product.getProduct_name());
        productDTO.setDescription(product.getDescription());
        productDTO.setCreate_at(product.getCreate_at());
        productDTO.setImages(product.getImages());
        productDTO.setUpdate_at(product.getUpdate_at());
        productDTO.setPrice(product.getPrice());

        return productDTO;
    }

    public static Product toProduct(CreateProduct productDTO){
        Product product = new Product();

        product.setProduct_name(productDTO.getProduct_name());
        product.setDescription(productDTO.getDescription());

        product.setImages(productDTO.getImages());

        product.setPrice(productDTO.getPrice());

        return product;
    }

}
