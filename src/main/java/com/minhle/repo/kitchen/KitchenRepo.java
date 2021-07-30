package com.minhle.repo.kitchen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.minhle.model.kitchen.Kitchen;
 
@Repository
public class KitchenRepo  {
    
	
	@Autowired
    private DynamoDBMapper dynamoDBMapper;
	
	
	public Kitchen    saveKitchen(Kitchen k){
   	 
	
		dynamoDBMapper.save(k);
	    	 return k;
	     }
	
	
	public Kitchen getbykitcheName(String name) {
		
		 Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			eav.put(":name", new AttributeValue().withS(name.toLowerCase()));
			DynamoDBScanExpression scanRequest = new DynamoDBScanExpression()	
														.withFilterExpression("kitchenName=:name")
														.withExpressionAttributeValues(eav); 
			List<Kitchen> list =  dynamoDBMapper.scan(Kitchen.class, scanRequest);
		 
			System.out.println(list.toString());
		 
		 
		 return list.get(0);
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