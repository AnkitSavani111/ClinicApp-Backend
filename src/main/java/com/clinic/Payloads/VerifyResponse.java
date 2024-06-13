package com.clinic.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerifyResponse {
    int user_id;
    String email;
    String role;
}

// uid, email, role