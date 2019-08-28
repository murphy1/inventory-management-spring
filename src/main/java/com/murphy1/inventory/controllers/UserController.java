package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.User;
import com.murphy1.inventory.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("user/new/user")
    public String saveAndUpdate(@ModelAttribute User user){
        User savedUser = userService.saveUser(user);

        return "redirect:/users.html";
    }

}
