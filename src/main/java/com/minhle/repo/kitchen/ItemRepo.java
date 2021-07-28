package com.minhle.repo.kitchen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
 

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.minhle.model.kitchen.Item;
 






public class ItemRepo {
  
	
	@Autowired
    private DynamoDBMapper dynamoDBMapper;
	public void saveItem(Item i){
		dynamoDBMapper.save(i);
     }
	 public Item getDetailOfOneItem(String id){
	    	return dynamoDBMapper.load(	Item.class, id) ;
	 }
	 
	 public List<Item> ListAllItem(){ 
		 DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
	    	return dynamoDBMapper.scan(Item.class, scanExpression); 
	  }
	 
	 
public  String updateItem(String id,Item i) { 
		 dynamoDBMapper.save(i, new DynamoDBSaveExpression() 
					 						.withExpectedEntry("id", new ExpectedAttributeValue( new AttributeValue().withS(id)
	                )));
	        return id;
				
			 
		 }
	    
}

