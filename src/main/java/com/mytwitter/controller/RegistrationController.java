package com.mytwitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/registration")
public class RegistrationController {

    @GetMapping
    public String registrationForm(){
        return "registration";
    }
}
