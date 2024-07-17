package com.julia.desafio_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julia.desafio_backend.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findUserByDocument(String document);
    Optional<User> findUserById(Long id);
}
