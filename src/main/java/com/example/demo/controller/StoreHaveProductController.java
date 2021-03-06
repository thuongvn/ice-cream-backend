package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.StoreHaveProduct;
import com.example.demo.model.detail.ListStoreHaveProduct;
import com.example.demo.model.detail.StoreHaveProductDto;
import com.example.demo.model.request.UpdateQuantityOfProduct;
import com.example.demo.service.ProductService;
import com.example.demo.service.StoreHaveProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store-have")
@Api(value = "Store have product APIs")
public class StoreHaveProductController {
    @Autowired
    private StoreHaveProductService storeHaveProductService;

    @ApiOperation(value = "create product of store", response = StoreHaveProduct.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PostMapping("/{id}")
    public ResponseEntity<?> createProduct(@RequestBody StoreHaveProduct storeHaveProduct,@PathVariable int id) {
        StoreHaveProductDto s = storeHaveProductService.createStoreHaveProduct(storeHaveProduct,id);
        return ResponseEntity.ok(s);
    }

    @ApiOperation(value = "update product of store", response = StoreHaveProduct.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody UpdateQuantityOfProduct storeHaveProduct) {
        StoreHaveProductDto s = storeHaveProductService.updateStoreHaveProduct(storeHaveProduct);
        if(s!=null){
            return ResponseEntity.ok(s);
        }else{
            return ResponseEntity.ok("update faill");
        }

    }

    @ApiOperation(value = "list all product of store", response = StoreHaveProduct.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("")
    public ResponseEntity<?> listAll(@RequestParam int store_id,
                                     @RequestParam(defaultValue = "1") int page) {
        ListStoreHaveProduct listStoreHaveProduct = storeHaveProductService.getAllStoreHaveProduct(store_id,page-1);
        if(listStoreHaveProduct!=null){
            return ResponseEntity.ok(storeHaveProductService.getAllStoreHaveProduct(store_id,page-1));
        }
        return ResponseEntity.ok("Not found ");
    }
}
