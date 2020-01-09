package com.example.demo.controller;



import com.example.demo.entity.Product;
import com.example.demo.entity.Store;

import com.example.demo.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
@Api(value = "Store APIs")
public class StoreController {

    @Autowired(required = false)
    private StoreService storeService;

    @ApiOperation(value = "create a store", response = Store.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PostMapping("")
    public ResponseEntity<?> createStore(@RequestBody Store store) {
        Store stores = storeService.createStore(store);
        return ResponseEntity.ok(stores);
    }

    @ApiOperation(value = "Update info of a store", response = Store.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStore(@RequestBody Store store, @PathVariable int id) {
        Store stores = storeService.updateStore(store, id);
        return ResponseEntity.ok(stores);
    }

    @ApiOperation(value = "Delete a store by id", response = Store.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteStore(@PathVariable int id) {
        boolean result = storeService.deleteStore(id);
        return (ResponseEntity) ResponseEntity.ok(result == true ? "success" : "unsuccess");
    }

    @ApiOperation(value = "Get a store by id", response = Store.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id){
        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    @ApiOperation(value = "Get all of store", response = Store.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })

    @GetMapping("")
    public ResponseEntity<?> getAllProduct(){
        return ResponseEntity.ok(storeService.getAllStore());
    }
}
