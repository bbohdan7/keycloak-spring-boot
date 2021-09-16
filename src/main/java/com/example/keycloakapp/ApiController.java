package com.example.keycloakapp;

import com.example.keycloakapp.models.Token;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ApiController {

    @GetMapping
    @RequestMapping("/admin")
    public ResponseEntity<String> adminPage() {
        return ResponseEntity.ok("Admin Page");
    }

    @GetMapping
    @RequestMapping("/token")
    public ResponseEntity<String> token() throws IOException {
        return ResponseEntity.ok(new AuthServerRequester().accessResource("http://192.168.0.128:8081/customers", HttpMethod.GET));
    }


}
