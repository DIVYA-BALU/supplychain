package com.example.jwt.repository;

import org.springframework.stereotype.Repository;

import com.example.jwt.Entity.OrderDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface OrderDetailsRepository extends MongoRepository<OrderDetails, String> {
    OrderDetails findByOrderID(String orderID);
}
