package com.example.keycloakapp;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HueMoe {

    @PostConstruct
    public void init() {
        System.out.println("Constructed successfully!!!");
    }
}
