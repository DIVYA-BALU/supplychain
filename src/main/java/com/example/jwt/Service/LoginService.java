package com.example.jwt.Service;

import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    public String getToken(String username,String pwd);
    public String extractToken(String authorizationHeader);
    public boolean validateToken(String token,String username);
}
