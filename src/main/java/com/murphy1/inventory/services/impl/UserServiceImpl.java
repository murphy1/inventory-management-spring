package com.murphy1.inventory.services.impl;

import com.murphy1.inventory.exceptions.BadRequestException;
import com.murphy1.inventory.exceptions.NotFoundException;
import com.murphy1.inventory.model.User;
import com.murphy1.inventory.model.Wallet;
import com.murphy1.inventory.repositories.UserRepository;
import com.murphy1.inventory.repositories.WalletRepository;
import com.murphy1.inventory.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private WalletRepository walletRepository;

    public UserServiceImpl(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
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

        if (!user.getPassword().equals(user.getPasswordCheck())){
            throw new BadRequestException("Passwords do not match!");
        }

        List<String> usernames = userRepository.findAll().stream()
                .map(user1 -> user1.getUsername().toLowerCase())
                .collect(Collectors.toList());

        if (usernames.contains(user.getUsername().toLowerCase())){
            throw new BadRequestException("Username: "+user.getUsername()+" already exists!");
        }

        Long idCheck = user.getId();

        if (idCheck == null){
            Wallet wallet = new Wallet();
            wallet.setUser(user);
            wallet.setBalance(0.0);
            walletRepository.save(wallet);
            user.setWallet(wallet);
            user.setRoles("ADMIN");
            user.setActive(true);
        }else{
            for (Wallet wallet : walletRepository.findAll()){
                if (wallet.getUser().getId().equals(user.getId())){
                    user.setWallet(wallet);
                }
            }
        }

        userRepository.save(user);

        return user;
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> returnedUser = userRepository.findById(id);

        if (!returnedUser.isPresent()){
            log.error("User not found with id: "+id);
            throw new NotFoundException("User not found!");
        }

        return returnedUser.get();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
