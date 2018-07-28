package com.pilicon.user.dto;

import lombok.Data;

@Data
public class UserInfoDto {
    private String id;

    private String username;

    private String password;

    private String openId;

    private Integer role;
}
