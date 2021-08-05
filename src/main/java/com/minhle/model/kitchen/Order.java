package com.minhle.model.kitchen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date; 
import org.springframework.stereotype.Component; 
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute; 
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEpochDate; 
import com.minhle.model.kitchen.converter.ListItemConverter; 
import lombok.Data;
import lombok.EqualsAndHashCode; 
import lombok.ToString;

 
@Component
@Data 
@ToString 
@EqualsAndHashCode 
public class Order implements Serializable{  
	
	 @DynamoDBTypeConverted(converter = ListItemConverter.class)
	 ArrayList<Item> Items;
	 @DynamoDBTypeConvertedEpochDate
	 Date date;
	 @DynamoDBAttribute
	 String userEmail;
	 
	 double total; 
	 public Order() {
	 	super();  
	 	Items = new ArrayList<Item>(); 
	 	date =  new Date();
		userEmail = "empty";
		total = 0;
	 }
	public Order(ArrayList<Item> items, String userEmail, double total) {
		super();
		Items = items;
		this.userEmail = userEmail;
		this.total = total;
		date =  new Date();
	}
	 
	 
}
