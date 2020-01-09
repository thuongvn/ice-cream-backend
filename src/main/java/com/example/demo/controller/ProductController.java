package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Api(value = "Product APIs")
public class ProductController {
    @Autowired
    private ProductService productService;

    @ApiOperation(value = "create a product", response = Product.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody Product createProductRequest) {
        Product product = productService.createProduct(createProductRequest);
        return ResponseEntity.ok(product);
    }

    @ApiOperation(value = "Update info of a product", response = Product.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product_update, @PathVariable int id) {
        Product product = productService.updateProduct(product_update, id);
        return ResponseEntity.ok(product);
    }

    @ApiOperation(value = "Delete a product by id", response = Product.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id) {
        boolean result = productService.deleteProduct(id);
        return (ResponseEntity) ResponseEntity.ok(result == true ? "success" : "unsuccess");
    }

    @ApiOperation(value = "Get a product by id", response = Product.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @ApiOperation(value = "Get all of product", response = Product.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("")
    public ResponseEntity<?> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @ApiOperation(value = "Get products by free text", response = Product.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("/search")
    public ResponseEntity<?> getProductByFreeText(@RequestParam String searching){
        List<Product> p = productService.getProducstByFreeText(searching);
        return ResponseEntity.ok(p);
    }
}
