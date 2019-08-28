package com.murphy1.inventory.services.impl;

import com.murphy1.inventory.model.User;
import com.murphy1.inventory.repositories.UserRepository;
import com.murphy1.inventory.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Transactional
    @Override
    public User saveUser(User user) {

        userRepository.save(user);

        return user;
    }
}
