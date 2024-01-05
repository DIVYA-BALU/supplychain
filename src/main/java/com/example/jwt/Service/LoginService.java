package com.example.jwt.Service;

import org.springframework.stereotype.Service;

import com.example.jwt.Entity.User;

@Service
public interface LoginService {

    public String register(User user);
    //public String getToken(User user);
    //public String extractToken(String authorizationHeader);
    //public boolean validateToken(String token,String username);
    public String login(User user);
}
