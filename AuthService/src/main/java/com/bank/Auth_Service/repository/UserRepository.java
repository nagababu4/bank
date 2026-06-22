package com.bank.Auth_Service.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.Auth_Service.entity.User;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    //boolean existsByEmail(String email);

	String findByEmail(String string);
}