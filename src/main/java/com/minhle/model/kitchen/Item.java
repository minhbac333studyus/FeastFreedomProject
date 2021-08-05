package com.minhle.model.kitchen;

import java.io.Serializable;
 
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute; 
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode; 
@Component
@Data 
@AllArgsConstructor
@EqualsAndHashCode
public class Item implements Serializable {  
 
	 private String kitchenName;
 
	 private String name;
 
	 private boolean vegOption;
	 
	 private Double price;  
	 
	public Item() {
		super();
		name = "empty";
		vegOption = false;
		price = 0.0;
		kitchenName ="empty";
		
	}
 
	 

}
 