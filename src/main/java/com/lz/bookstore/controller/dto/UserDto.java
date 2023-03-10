package com.lz.bookstore.controller.dto;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
    private String token;
    private String type;
    private Integer id;
}
