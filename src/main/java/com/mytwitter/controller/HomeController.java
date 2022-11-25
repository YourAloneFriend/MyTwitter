package com.mytwitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String main(){
        return "main";
    }

    @GetMapping("/home")
    public String home(){ return "home"; }

    @GetMapping("/authentication")
    public String authentication(){
        return "authentication";
    }
}
