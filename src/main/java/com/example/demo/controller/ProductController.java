package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.model.detail.ListProductDto;
import com.example.demo.model.detail.ProductDTO;
import com.example.demo.model.request.CreateProduct;
import com.example.demo.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<?> createProduct(@RequestBody @Valid CreateProduct createProductRequest) {
        ProductDTO productDTO = productService.createProduct(createProductRequest);
        if(productDTO!=null){
            return ResponseEntity.ok(productDTO);
        }else{
            return ResponseEntity.ok("create product faill");
        }

    }

    @ApiOperation(value = "Update info of a product", response = Product.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody @Valid CreateProduct product_update, @PathVariable int id) {
        ProductDTO product = productService.updateProduct(product_update, id);
        if(product!=null){
            return ResponseEntity.ok(product);
        }else {
            return ResponseEntity.ok("product can not update");
        }

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


    @ApiOperation(value = "Get products for mobile", response = Product.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("/mobile")
    public ResponseEntity<?> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProduct());
    }


    @ApiOperation(value = "Get all of product, can search", response = Product.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("")
    public ResponseEntity<?> getAllProduct(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(defaultValue = "1") int page){
        ListProductDto productDtos = productService.getAllProducst(keyword,page-1);
        if(productDtos!=null){
            return ResponseEntity.ok(productDtos);
        }else {
            return ResponseEntity.ok("Faill");
        }

    }
}
