package com.julia.desafio_backend.DTOs;

import java.math.BigDecimal;

import com.julia.desafio_backend.domain.user.UserType;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
    
}
