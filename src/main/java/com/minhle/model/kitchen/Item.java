package com.minhle.model.kitchen;

import java.io.Serializable;
 
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute; 
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data; 
@Component
@Data 

public class Item implements Serializable {  
	@DynamoDBAttribute
	 private String kitchenName;
	@DynamoDBAttribute
	 private String name;
	@DynamoDBAttribute
	 private boolean vegOption;
	@DynamoDBAttribute
	 private Double price;  
	 
	public Item() {
		super();
		name = "empty";
		vegOption = false;
		price = 0.0;
	}
 
	@Override
	public String toString() {
		return  name+" , " +   vegOption +  " , " + price + " , " + kitchenName  ;
	} 
	@Override
	public int hashCode() {
		return name.hashCode();
	}

}
class ItemConverter implements DynamoDBTypeConverter<String, Item>
{
	private static final ObjectMapper mapper = new ObjectMapper();
	@Override
	public String convert(Item object) {
		try {
			return mapper.writeValueAsString(object);
		}
		catch(JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		 
	}

	@Override
	public Item unconvert(String ItemUnconvert) {
		// TODO Auto-generated method stub
		try {
			return mapper.readValue(ItemUnconvert, Item.class);
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
