package com.example.demo.service;

import com.example.demo.model.Quote;
import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    void createUser(User user);
    void updateUserById(Long id, User user);
    void deleteUserById(Long id);
    void addQuote(Long id, User user, Quote quote);
}
