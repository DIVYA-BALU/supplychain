package com.example.jwt.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.jwt.Filter.AuthFilter;
import com.example.jwt.Service.Implementation.UserEntityServiceImplementation;

@Configuration
public class SecurityConfig {
    @Autowired
    UserEntityServiceImplementation userEntityService;
    @Autowired
    AuthFilter authFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
        security.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(request->request.requestMatchers("/login/**")
                                                        .permitAll()
                                                        .anyRequest()
                                                        .authenticated())
                .sessionManagement(management -> management
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return security.build();    

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userEntityService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }
} 
//   @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         return http.csrf(csrf -> csrf.disable())
//                 .authorizeHttpRequests(requests -> requests
//                         .requestMatchers().permitAll())
//                 .authorizeHttpRequests(requests -> requests.requestMatchers().authenticated())
//                 .authorizeHttpRequests(requests -> requests.requestMatchers().authenticated())
//                 .sessionManagement(management -> management
//                         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authenticationProvider(authenticationProvider())
//                 .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
//                 .build();
//     }