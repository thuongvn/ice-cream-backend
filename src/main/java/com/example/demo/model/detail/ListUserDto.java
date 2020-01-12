package com.example.demo.model.detail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListUserDto {
    private List<UserDto> list;
    private int totalPages;
    private long totalItems;
}
