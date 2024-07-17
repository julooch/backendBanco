package com.julia.desafio_backend.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.julia.desafio_backend.DTOs.UserDTO;
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

    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers(){
       return this.userRepository.findAll();
    }
    
}
