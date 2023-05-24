package com.codingGenius.coding_genius.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDto {
    private String name;
    private String email;
    private String password;
}
