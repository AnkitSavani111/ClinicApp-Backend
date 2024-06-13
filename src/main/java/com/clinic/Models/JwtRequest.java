package com.clinic.models;

import lombok.Data;

@Data
public class JwtRequest {
    String email;
    String password;
}
