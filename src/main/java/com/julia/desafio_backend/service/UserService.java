package com.julia.desafio_backend.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julia.desafio_backend.domain.user.User;
import com.julia.desafio_backend.domain.user.UserType;
import com.julia.desafio_backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        if(sender.getUserType() != UserType.MERCHANT){
            throw new Exception("Usuário lojista não está autorizado a realizar uma transação!");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente para realizar uma transação");
        }
    }

    public User findUserById(Long id) throws Exception{
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void saveUser(User user){
        this.userRepository.save(user);
    }
    
}
