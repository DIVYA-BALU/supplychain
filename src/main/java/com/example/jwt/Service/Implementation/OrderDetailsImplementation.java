package com.example.jwt.Service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Exception.NoOrderFoundException;
import com.example.jwt.Entity.OrderDetails;
import com.example.repository.OrderDetailsRepository;
import com.example.jwt.Service.OrderDetailsService;

@Service
public class OrderDetailsImplementation implements OrderDetailsService{
    @Autowired
	OrderDetailsRepository orderDetailsRepo;
	
	
	public OrderDetails getById(String id) {
		OrderDetails details=orderDetailsRepo.findById(id).get();
		if(details!=null)
			return details;
		else
			throw new NoOrderFoundException("No Orders found for given ID");
	}
	
	public String saveOrder(OrderDetails a) {
		OrderDetails id = orderDetailsRepo.findByOrderID(a.getOrderID());
		if(id==null)
		{
			orderDetailsRepo.save(a);
			return "OrderDetails saved successfully : "+a.get_id();
	    }
		else
		{
			return "OrderDetails already exists";
		}
	}
	
	public String updateOrder(OrderDetails a) {
		OrderDetails id = orderDetailsRepo.findByOrderID(a.getOrderID());
		if(id!=null)
		{
			orderDetailsRepo.save(a);
			return "OrderDetails Updated successfully : "+id.get_id();
	    }
		else
		{
			return "OrderDetails doesn't exists";
		}
	}
	
	public String deleteOrder(String id) {
		OrderDetails _id = orderDetailsRepo.findByOrderID(id);
		if(_id!=null)
		{
			orderDetailsRepo.deleteById(_id.get_id());
			return "OrderDetails deleted successfully : "+_id.get_id();
	    }
		else
		{
			return "OrderDetails doesn't exists";
		}
	}
	
	public List<OrderDetails> getAllOrder() {
		return orderDetailsRepo.findAll();
	}
}
