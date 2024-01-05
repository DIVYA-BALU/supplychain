package com.example.jwt.Filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.jwt.Service.Implementation.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@EnableWebSecurity
@EnableMethodSecurity
public class AuthFilter extends OncePerRequestFilter{

    // public boolean validateToken(String token) {
    //     try{
    //         System.out.println( "token "+token);
    //                     Jwts.parser()
    //                     .setSigningKey("secret")
    //                     .parseClaimsJws(token)
    //                     .getBody();
    //     return true;
    // }catch(Exception e){
    //     return false;
    // }
    //}
    @Autowired
    JwtService jwtservice;
    @Autowired
    UserDetailsService service;

    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if(authHeader == null || !authHeader.startsWith("Bearer "))
        {
            filterChain.doFilter(request, response);
            return;
        } 
        jwt = authHeader.substring(7);
        userEmail = jwtservice.extractUserName(jwt);    //todo  extract the UserEmail from jwt token
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            UserDetails userDetails = this.service.loadUserByUsername(userEmail);
         System.out.println(userDetails);
            //Boolean isTokenvalid = tokenRepository.findByToken(jwt).map(t -> !t.getExpired() && !t.getRevoked()).orElse(false);
            if(jwtservice.isTokenvalid(jwt, userDetails))
            {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
        }
        filterChain.doFilter(request, response);
    }

        // @Override
        // protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        //         FilterChain filterChain) throws ServletException, IOException {
        //     String authHeader=request.getHeader("Authorization");
        //     if(authHeader==null || !authHeader.startsWith("Bearer "))
        //     {
        //         filterChain.doFilter(request, response);
        //         return;
        //     }
        //     String token = authHeader.substring(7);
        //     Boolean validationResponse=validateToken(token);
            
        //     if(!validationResponse){
        //         throw new RestClientException("Unauthorized Exception");
        //     }
        //     filterChain.doFilter(request, response);
            
        // }
}

// @Override
//     protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {

//         final String authHeader = request.getHeader("Authorization");
//         final String jwt;
//         final String userEmail;
//         if(authHeader == null || !authHeader.startsWith("Bearer "))
//         {
//             filterChain.doFilter(request, response);
//             return;
//         } 
//         jwt = authHeader.substring(7);
//         userEmail = jwtservice.extractUserName(jwt);    //todo  extract the UserEmail from jwt token
//         if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null)
//         {
//             UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//             Boolean isTokenvalid = tokenRepository.findByToken(jwt).map(t -> !t.getExpired() && !t.getRevoked()).orElse(false);
//             if(jwtservice.isTokenvalid(jwt, userDetails) && isTokenvalid)
//             {
//                 UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
//                 authToken.setDetails(
//                     new WebAuthenticationDetailsSource().buildDetails(request)
//                 );

//                 SecurityContextHolder.getContext().setAuthentication(authToken);

//             }
//         }
//         filterChain.doFilter(request, response);
//     }

// }
// else
//             {
// //                    String username = loginService.getUsernameFromJWT(token);
// //    String userType = loginService.getUserTypeFromJWT(token);
// //    customUserDetailsService.setUserType(UserType.valueOf(userType));
// //    UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
// //    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, 
// //                    null, userDetails.getAuthorities());
   
//    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//             }