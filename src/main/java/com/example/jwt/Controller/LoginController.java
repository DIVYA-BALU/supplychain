package com.example.jwt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.Entity.User;
import com.example.jwt.Service.LoginService;


@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        return ResponseEntity.ok(loginService.register(user));
    }
    @PostMapping("/userlogin")
    public ResponseEntity<String> login(@RequestBody User user){
        return ResponseEntity.ok(loginService.login(user));
    }
    // @GetMapping("/validate/{username}")
    // public ResponseEntity<String> tokenValidation(@RequestHeader("Authorization") String authorizationHeader,@PathVariable String username) {
    //     String token=loginService.extractToken(authorizationHeader);

    //     if (loginService.validateToken(token,username)) {
    //         return ResponseEntity.ok("Valid token");
    //     } 
    //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    // }

}
