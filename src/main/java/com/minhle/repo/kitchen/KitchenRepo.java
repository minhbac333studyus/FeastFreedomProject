package com.minhle.repo.kitchen; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
		eav.put(":kitchenName", new AttributeValue().withS(name));
		DynamoDBScanExpression scanRequest = new DynamoDBScanExpression()	
													.withFilterExpression("kitchenName=:kitchenName")
														.withExpressionAttributeValues(eav); 
		List<Kitchen> list =  dynamoDBMapper.scan(Kitchen.class, scanRequest);
		if(list.size() ==0) {
			return new Kitchen();
		} 
		return list.get(0);
	}
	public List<Kitchen> getAllKitchenByProviderEmail(String providerEmail) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":providerEmail", new AttributeValue().withS(providerEmail));
		DynamoDBScanExpression scanRequest = new DynamoDBScanExpression()	
													.withFilterExpression("providerEmail=:providerEmail")
													.withExpressionAttributeValues(eav); 
		List<Kitchen> list =  dynamoDBMapper.scan(Kitchen.class, scanRequest); 
		System.out.println(list.toString());
		return list;
	}
	
	 public Kitchen getDetailOfOneKitchen(String id){ 
	    	return dynamoDBMapper.load(Kitchen.class, id) ;
	    } 
	 public List<Kitchen>ListAllKitchen(){
		  
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
	 public void deleteKitchenByKitchenName(String kitchenName) {
		 Kitchen kitchen = this.getbykitcheName(kitchenName);
		 if(kitchen.getKitchenName() == "empty") {
			 System.out.println("No kitchen " + kitchenName +" to delete");
		 }
		 else {
			 dynamoDBMapper.delete(kitchen);
		 }
		
	 }	
	 @Autowired
	ItemRepo itemRepo;
 
	public void addItemToKitchenMenu(String kitchenName, Item item) { 
		Kitchen kit = this.getbykitcheName(kitchenName);  
		kit.getMenu().add(item);
		this.saveKitchen(kit);   
	}
	 
	 
	 
}