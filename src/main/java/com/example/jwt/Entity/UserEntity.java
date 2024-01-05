// package com.example.jwt.Entity;

// import java.util.Collection;
// import java.util.List;


// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// public class UserEntity implements UserDetails{

//     private List<GrantedAuthority> grantedAuthority;
//     public UserEntity(User user){
//         super();
//         // grantedAuthority=List.of(new SimpleGrantedAuthority(super.getRole()));
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return List.of(new SimpleGrantedAuthority(super.getRole().name()));
//     }

//     @Override
//     public String getPassword() {
//         return super.getPassword();
//     }

//     @Override
//     public String getUsername() {
//         return super.getUserName();
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//             return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//          return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;
//     }
    
// }
