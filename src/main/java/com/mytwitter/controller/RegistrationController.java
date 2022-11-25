package com.mytwitter.controller;

import com.mytwitter.dto.UserDTO;
import com.mytwitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserDTO userDTO(){
        return new UserDTO();
    }

    @GetMapping
    public String registrationForm(){
        return "registration";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") UserDTO userDTO){
        if(userService.findUserByLogin(userDTO) != null)
            throw new IllegalArgumentException("User with this login is already signed up!");
        if(userService.findUserByEmail(userDTO) != null)
            throw new IllegalArgumentException("User with this email is already signed up!");
        userService.createUser(userDTO);
        return "redirect:/authentication?success";
    }
}
