package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.detail.ListUserDto;
import com.example.demo.model.detail.UserDto;
import com.example.demo.model.request.CreateUserRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public List<UserDto> getAllUserForMobile();
    public UserDto author(int id);
    public String saveImage(MultipartFile imageFile) throws Exception;
    public boolean changePassword(String old_password, String new_password, int user_id);
//    public User createUser(User createUserRequest);
//    public User updateUser(CreateUserRequest createUserRequest);
//    public User deleteUser(int id);
    public UserDto getUserByEmail(String email);
//    public List<User> getListUser(String keyword, int page);
}
