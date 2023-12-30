package com.example.jwt.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.Entity.OrderDetails;
import com.example.jwt.Service.OrderDetailsService;

@RestController
@RequestMapping("/orderdetails")
public class OrderDetailsController {
    @Autowired
	OrderDetailsService orderDetailsService;
	
	@GetMapping("/getallorder")
	public List<OrderDetails> readAllOrder()
	{
			return orderDetailsService.getAllOrder();
		
	}

    @GetMapping("/select/orderbyid/{id}")
	public OrderDetails getById(@PathVariable("id") String id){
		return orderDetailsService.getById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> createAccount(@RequestBody OrderDetails a)
	{
		try{
			return new ResponseEntity<String>(orderDetailsService.saveOrder(a),HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>("Internal Error",HttpStatus.BAD_REQUEST);
		}
	}
    @PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody OrderDetails a) {
		try{
			return new ResponseEntity<>(orderDetailsService.updateOrder(a),HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>("Internal Error",HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/orderbyid/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") String id) {
		try{
			return new ResponseEntity<>(orderDetailsService.deleteOrder(id),HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>("Internal Error",HttpStatus.BAD_REQUEST);
		}
	}
}
