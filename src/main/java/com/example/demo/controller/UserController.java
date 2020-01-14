package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.model.detail.ListUserDto;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(value = "User APIs")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "get user want to become store", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("/get-user-register")
    public ResponseEntity<?> getUserRegister() {
        return ResponseEntity.ok(userService.getCustomerWantToStore());
    }

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


    @ApiOperation(value = "create a User : \t\"email\":\"test@gmail.com\",\n" +
            "\t\"password\": \"123456\",\n" +
            "\t\"fullName\": \"Sam Smith\",\n" +
            "\t\"address\": \"Ha Noi\",\n" +
            "\t\"numberphone\": \"0916016972\",\n" +
            "\t\"role\":\"CUSTOMER\",\n" +
            "\t\"birthday\":\"1997/10/24\"", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody @Valid CreateUserRequest createUserRequest) {

        UserDto userDto = userService.createUser(createUserRequest);
        return ResponseEntity.ok(userDto);
    }

    @ApiOperation(value = "Get user by id", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("/get-info-user")
    public ResponseEntity<?> getUserById(@RequestParam int id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @ApiOperation(value = "Delete user by id", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @DeleteMapping("")
    public ResponseEntity<?> deleteUserById(@RequestParam int id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }

//    @ApiOperation(value = "Get list of user", response = UserDto.class)
//    @ApiResponses({
//            @ApiResponse(code = 400, message = "Bad request"),
//            @ApiResponse(code = 500, message = "Internal Server Error"),
//    })
//    @GetMapping("")
//    public ResponseEntity<?> getListUser(){
//        return ResponseEntity.ok(userService.getAllUser());
//    }

    @ApiOperation(value = "Update user by email", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @PutMapping("")
    public ResponseEntity<?> updatetUserById(@RequestBody CreateUserRequest createUserRequest){

        UserDto user = userService.updateUser(createUserRequest);
        if(user !=null){
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.ok("update faill");
        }

    }


    @ApiOperation(value = "Get a list of user", response = UserDto.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 500, message="Internal Server Error")
    })
    @GetMapping("")
    public ResponseEntity<?> getListUser(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(defaultValue = "1") int page) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int id = (Integer)auth.getCredentials();
        ListUserDto users = userService.getAllUser(keyword, page-1);
        return ResponseEntity.ok(users);
    }

}
