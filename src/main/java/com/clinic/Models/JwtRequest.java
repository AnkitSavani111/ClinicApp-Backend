package com.clinic.Models;

import lombok.Data;

@Data
public class JwtRequest {
    String email;
    String password;
}
