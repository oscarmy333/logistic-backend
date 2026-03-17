package com.marvisa.logistic.security.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserMeResponse {
    private Long id;
    private String codigo;
    private String fullName;
    private String username;
    private String email;
    private Set<String> roles;
}