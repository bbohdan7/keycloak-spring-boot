package com.example.keycloakapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping
    public String hello() {
        return "hello";
    }

    @GetMapping("/customers")
    public String index(Principal principal, Model model) {
        model.addAttribute("user", "John");
        //model.addAttribute("username", principal.getName());
        return "home";
    }
}
