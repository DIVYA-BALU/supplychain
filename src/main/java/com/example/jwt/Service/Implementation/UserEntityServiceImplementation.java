package com.example.jwt.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.jwt.Entity.User;
import com.example.jwt.repository.UserRepository;

@Service
public class UserEntityServiceImplementation implements UserDetailsService{

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDetails= userRepository.findByUserName(username).get();
        return userDetails;
    }
    
}
