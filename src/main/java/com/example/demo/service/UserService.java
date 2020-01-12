package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.detail.ListUserDto;
import com.example.demo.model.detail.UserDto;
import com.example.demo.model.request.CreateUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public UserDto login(String email, String password);
    public UserDto createUser(CreateUserRequest createUserRequest);
    public UserDto updateUser(CreateUserRequest createUserRequest);
    public UserDto deleteUser(int id);
    public UserDto getUserById(int id);
    public List<UserDto> getCustomerWantToStore();
    public ListUserDto getAllUser(String keyword, int page);

//    public User createUser(User createUserRequest);
//    public User updateUser(CreateUserRequest createUserRequest);
//    public User deleteUser(int id);
//    public User getUserById(int id);
//    public List<User> getListUser(String keyword, int page);
}
