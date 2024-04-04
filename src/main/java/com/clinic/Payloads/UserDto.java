package com.clinic.Payloads;

import com.clinic.Models.Role;

import lombok.Data;

@Data
public class UserDto {
    private int user_id;

    private String uname;

    private String email;

    private String password;

    private Role role;
}
