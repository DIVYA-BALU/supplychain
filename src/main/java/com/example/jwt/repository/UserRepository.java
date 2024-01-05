package com.example.jwt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.jwt.Entity.User;
import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User,String>{
    Optional<User> findByUserName(String userName);
    
}
