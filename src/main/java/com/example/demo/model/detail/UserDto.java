package com.example.demo.model.detail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private String email;
    private String full_name;
    private String phone;
//    private float total_cash;
    private String avatar;
}
