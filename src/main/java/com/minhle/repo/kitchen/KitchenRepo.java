package com.minhle.repo.kitchen;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
 
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.minhle.model.kitchen.Kitchen;

 
public class KitchenRepo  {
    
	
	@Autowired
    private DynamoDBMapper dynamoDBMapper;
	
	
	public void    saveKitchen(Kitchen k){
   	 
		dynamoDBMapper.save(k);
	    	 
	     }
	
	 public Kitchen getDetailOfOneKitchen(String id){
	    	
	    	return dynamoDBMapper.load(Kitchen.class, id) ;
	    }
	    
	 
	 
	 
	 public List<Kitchen>	  ListAllKitchen(){
		  
		 DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
	    	return dynamoDBMapper.scan(Kitchen.class, scanExpression);
		  
	  }
	 
	 
	 public  String updateKitchen(String id,Kitchen K) {
		 
		 
		 dynamoDBMapper.save(K,
	                new DynamoDBSaveExpression()
	        .withExpectedEntry("id",
	                new ExpectedAttributeValue(
	                        new AttributeValue().withS(id)
	                )));
	        return id;
				
			 
		 }
}
