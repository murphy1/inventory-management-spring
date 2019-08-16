package com.murphy1.inventory.controllers;

import com.murphy1.inventory.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping({"/user", "/users", "/user.html", "/users.html"})
    public String getAllUsers(){
        return "user";
    }

}
