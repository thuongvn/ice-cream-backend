package com.example.demo.model.mapper;

import com.example.demo.entity.User;
import com.example.demo.model.detail.UserDto;

public class UserMapper {
    public static UserDto toUserDto(User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setAvatar(user.getAvatar());
        userDto.setEmail(user.getEmail());
        userDto.setFull_name(user.getFull_name());
        userDto.setPhone(user.getNumberphone());
        userDto.setRole(user.getRoles());
        userDto.setStatus(user.getStatus());
        userDto.setGender(user.getGender());
        userDto.setAddress(user.getAddress());
        userDto.setBirthday(user.getBirthday());
        return userDto;
    }
}
