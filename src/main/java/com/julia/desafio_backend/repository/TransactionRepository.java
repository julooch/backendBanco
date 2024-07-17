package com.julia.desafio_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julia.desafio_backend.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    
    
}
