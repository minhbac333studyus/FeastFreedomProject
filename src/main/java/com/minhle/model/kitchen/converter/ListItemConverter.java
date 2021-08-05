package com.minhle.model.kitchen.converter;

import java.util.ArrayList;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.minhle.model.kitchen.Item; 
public class ListItemConverter implements DynamoDBTypeConverter<String, ArrayList<Item>>{

	private static final ObjectMapper mapper = new ObjectMapper();
	@Override
	public String convert(ArrayList<Item> object) {
		// TODO Auto-generated method stub
		String result = ""; 
			 ObjectWriter ob= mapper.writer().withDefaultPrettyPrinter();
			 try {
				result =  ob.writeValueAsString(object);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		 return result; 
	}

	@Override
	public ArrayList<Item> unconvert(String object) {
		// TODO Auto-generated method stub
		try {
			return mapper.readValue(object, new TypeReference<ArrayList<Item>>() {});
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

}
