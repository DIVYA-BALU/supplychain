package com.example.jwt.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/demo/api")
public class HelloController {
    
    @GetMapping("/hello/{name}")
    public ResponseEntity<?> hello(HttpServletRequest req, @PathVariable String name) {
        req.getHeader("Authorization");
        return ResponseEntity.ok("hello!");
    }

    @GetMapping("/greet")
    public ResponseEntity<?> greet(){
        return ResponseEntity.ok("Welcome");
    }
    
}
