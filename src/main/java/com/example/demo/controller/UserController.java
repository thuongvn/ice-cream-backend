package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.model.detail.UserDto;
import com.example.demo.model.request.CreateUserRequest;
import com.example.demo.model.request.Login;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(value = "User APIs")
public class UserController {
    @Autowired(required = false)
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid Login createUserRequest) {
        // Gọi đến method trong service xử lý đăng nhập
        // Gen token trả về client bằng cách gọi hàm, truyền entity User vào
        // JwtUltis.generateToken(user);
        UserDto userDto = userService.login(createUserRequest.getEmail(), createUserRequest.getPassword());
        if(userDto == null){
            return ResponseEntity.ok("Login faill");
        }
        return ResponseEntity.ok(userDto);
    }


    @ApiOperation(value = "create a User", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody CreateUserRequest createUserRequest) {

        UserDto userDto = userService.createUser(createUserRequest);
        return ResponseEntity.ok(userDto);
    }

    @ApiOperation(value = "Get user by id", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @ApiOperation(value = "Delete user by id", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @ApiOperation(value = "Get list of user", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("")
    public ResponseEntity<?> getListUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @ApiOperation(value = "Update user by id", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updatetUserById(@RequestBody CreateUserRequest createUserRequest,@PathVariable int id){
        return ResponseEntity.ok(userService.updateUser(createUserRequest,id));
    }


}
