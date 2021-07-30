package com.minhle.repo.kitchen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

 
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.minhle.model.kitchen.Cart;
@Repository
public class CartRepo {

	
	@Autowired
    private DynamoDBMapper dynamoDBMapper;
	
	
	
	public void addedtocart(Cart c) {
		
	dynamoDBMapper.save(c);
		
		
	}
	
	
	public List<Cart> listofcart() {
		 DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
	    	return dynamoDBMapper.scan(Cart.class, scanExpression);
	}
	
	
	
	/*public double totalprice(Cart c) {
	
		
	}*/
	
}
