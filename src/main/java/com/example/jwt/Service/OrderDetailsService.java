package com.example.jwt.Service;

import java.util.List;

import com.example.jwt.Entity.OrderDetails;

public interface OrderDetailsService {
    
    List<OrderDetails> getAllOrder();
	
	OrderDetails getById(String _id);
	
	String saveOrder(OrderDetails a);
	
	String updateOrder(OrderDetails a);
	
	String deleteOrder(String id);
}
