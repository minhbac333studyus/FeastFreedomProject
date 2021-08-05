package com.minhle.model.kitchen.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minhle.model.kitchen.Item; 

public class ItemConverter implements DynamoDBTypeConverter<String, Item>{
	private static final ObjectMapper mapper = new ObjectMapper();
	@Override
	public String convert(Item object) {
		// TODO Auto-generated method stub
		 
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return"";
	}

	@Override
	public Item unconvert(String object) {
		// TODO Auto-generated method stub
		try {
			return mapper.readValue(object, Item.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Item();
	}

}
