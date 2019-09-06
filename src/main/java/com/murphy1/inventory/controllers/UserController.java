package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.User;
import com.murphy1.inventory.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
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
    public String saveAndUpdate(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError ->
                    log.debug(objectError.toString())
                    );
            return "forms/userform";
        }

        userService.saveUser(user);

        return "redirect:/users.html";
    }

    @GetMapping("user/delete/{userId}")
    public String deleteUserById(@PathVariable String userId){
        userService.deleteById(Long.valueOf(userId));

        return "redirect:/users.html";
    }

}
