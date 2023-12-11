package com.example.authentication.Model.dto;

import com.example.authentication.Model.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String firstName ;
    private String lastName ;
    private String email ;
    private String securePassword ;
    private Role role ;
}
