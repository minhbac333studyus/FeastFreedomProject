package com.minhle.repo.kitchen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.minhle.model.kitchen.Item;

 
@Repository
public class ItemRepo {
  
	
	@Autowired
    private DynamoDBMapper dynamoDBMapper;
	
	
	public void    saveItem(Item i){
	   	 
		dynamoDBMapper.save(i);
	}
	 
	  public Item getonebyname(String name) {
		 
		 Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			eav.put(":name", new AttributeValue().withS(name.toLowerCase()));
			DynamoDBScanExpression scanRequest = new DynamoDBScanExpression()	
														.withFilterExpression("itemname=:name")
														.withExpressionAttributeValues(eav); 
			List<Item> list =  dynamoDBMapper.scan(Item.class, scanRequest);
		 
			System.out.println(list.toString());
		 
		 
		 return list.get(0);
	 }
	 
	 public List<Item>	  ListAllItem(){
		  
		 DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
	    	return dynamoDBMapper.scan(Item.class, scanExpression);
		  
	  }
	 
	 
public  String updateItem(String id,Item i) {
		 
		 
		 dynamoDBMapper.save(i,
	                new DynamoDBSaveExpression()
	        .withExpectedEntry("itemid",
	                new ExpectedAttributeValue(
	                        new AttributeValue().withS(id)
	                )));
	        return id;
				
			 
		 }
	    
}

