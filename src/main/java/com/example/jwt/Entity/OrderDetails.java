package com.example.jwt.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    @Id
	private String _id;
	
	@Field("orderID")
	private String orderID;
	
	@Field("orderDate")
	private Date orderDate;
	
	@Field("paymentStatus")
	private String paymentStatus;
	
	@Field("deliveryStatus")
	private String deliveryStatus;
	
}
