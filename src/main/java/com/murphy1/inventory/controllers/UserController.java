package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.User;
import com.murphy1.inventory.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping({"/user", "/users", "/user.html", "/users.html"})
    public String getAllUsers(Model model){

        model.addAttribute("users", userService.getAllUsers());

        return "user";
    }

    @RequestMapping("user/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());

        return "forms/userform";
    }

    @GetMapping("user/update/{userId}")
    public String updateUser(@PathVariable String userId, Model model){
        model.addAttribute("user", userService.findUserById(Long.valueOf(userId)));

        return "forms/userform";
    }

    @PostMapping("user/new/user")
    public String saveAndUpdate(@ModelAttribute User user){
        User savedUser = userService.saveUser(user);

        return "redirect:/users.html";
    }

}
