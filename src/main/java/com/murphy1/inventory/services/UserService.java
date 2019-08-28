package com.murphy1.inventory.services;

import com.murphy1.inventory.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User saveUser(User user);
    User findUserById(Long id);
}
