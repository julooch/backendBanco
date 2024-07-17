package com.julia.desafio_backend.DTOs;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderId, Long receiverId) {
    
}
