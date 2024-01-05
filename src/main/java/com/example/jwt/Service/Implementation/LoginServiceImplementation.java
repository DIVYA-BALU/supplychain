package com.example.jwt.Service.Implementation;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwt.Entity.User;
import com.example.jwt.Exception.UserNotFoundException;
import com.example.jwt.Service.LoginService;
import com.example.jwt.repository.UserRepository;



@Service
public class LoginServiceImplementation implements LoginService{
    @Autowired
    UserRepository userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    public String register(User user){
        Optional<User> id= userRepo.findByUserName(user.getUsername());
        if(id.isPresent()){
            return "User already exists";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userDetails = userRepo.save(user);
        String token=jwtService.generateToken(userDetails);
        return token;
    }
    public String login(User user){
         Optional<User> id= userRepo.findByUserName(user.getUsername());
        if(!(id.isPresent())){
            throw new UserNotFoundException("No user found");
        }
        
        String token=jwtService.generateToken(user);;
        return token;
    }
    // public String getToken(User user) {
        

    //     // Map<String,Object> map=new HashMap<>();
    //     // String role="admin";
    //     // map.put("Role",role);
    //     Claims claim=Jwts.claims().setSubject(user.getUserName());
    //     String token=Jwts.builder().setClaims(claim).signWith(SignatureAlgorithm.HS256, "secret").compact();
    //     // Claims claims = Jwts.parser()
    //     //                 .setSigningKey("secret")
    //     //                 .parseClaimsJws(token)
    //     //                 .getBody();
    //     // claims.putAll(map);
    //     // String roleOfUser=claims.get("Role").toString();
    //     // System.out.println(roleOfUser);
    //     return token;
    // }

    // public String extractToken(String authorizationHeader) {
    //     if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
    //         return authorizationHeader.substring(7); 
    //     }
    //     return null;
    // }

    // public boolean validateToken(String token,String username) {
    //     System.out.println( "token "+token);
    //     // String tokengot=getToken(username,"");
    //     // if(tokengot.equals(token))
    //     //     return true;
    //     // return false;

    //     Claims claims = Jwts.parser()
    //                     .setSigningKey("secret")
    //                     .parseClaimsJws(token)
    //                     .getBody();
    //     String subject = claims.getSubject();
    //     System.out.println( "subject "+subject);
    //     if(subject.equals(username))
    //         return true;
    //     return false;
    // }
    
}
