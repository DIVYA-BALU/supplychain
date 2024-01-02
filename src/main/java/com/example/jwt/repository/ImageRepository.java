package com.example.jwt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.jwt.Entity.Image;

@Repository
public interface ImageRepository extends MongoRepository<Image,String>{

}
