package com.julia.desafio_backend.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.sound.midi.Receiver;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.julia.desafio_backend.DTOs.TransactionDTO;
import com.julia.desafio_backend.domain.transaction.Transaction;
import com.julia.desafio_backend.domain.user.User;
import com.julia.desafio_backend.pojo.AuthorizationResponse;
import com.julia.desafio_backend.repository.TransactionRepository;

@Service
public class TransactionService {
    
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());
        if(!isAuthorized){
            throw new Exception("Transação não autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.transactionRepository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);
    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
    ResponseEntity<AuthorizationResponse> authorizationResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", AuthorizationResponse.class);

    if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
        AuthorizationResponse body = authorizationResponse.getBody();
        if (body != null) {
            String message = body.getMessage();
            return "Autorizado".equalsIgnoreCase(message);
        }
    }
    return false;
}

    

}
