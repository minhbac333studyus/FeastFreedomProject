package com.minhle.repo.user; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;import java.util.Map; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository; 
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper; 
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.minhle.model.kitchen.Item;
import com.minhle.model.user.EndUser;
import com.minhle.repo.kitchen.ItemRepo;
import com.minhle.service.KitchenService;
 
@Repository
public class  EndUserRepository  { 
	final String TableName = "EndUser";
	@Autowired
    private DynamoDBMapper dynamoDBMapper; 
	@Autowired 
	private KitchenService kitchenService;
	@Autowired
	private ItemRepo itemService;
    public EndUser saveUser(EndUser user) {
        dynamoDBMapper.save(user);
        return user;
    }
    public List<EndUser> findAllUsers(){
    	
    	DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
    	return dynamoDBMapper.scan(EndUser.class, scanExpression);
    }  
	public EndUser findByEmail(String email )
    { 
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":Email", new AttributeValue().withS(email));
		DynamoDBScanExpression scanRequest = new DynamoDBScanExpression()	 
													.withFilterExpression("Email = :Email")
													.withExpressionAttributeValues(eav); 
		if(dynamoDBMapper.scan(EndUser.class, scanRequest).size() ==0) {
			System.out.println("No user was found. Return an empty user");
			return new EndUser();
		}
		
    	return dynamoDBMapper.scan(EndUser.class, scanRequest).get(0);
    } 
	
	public EndUser findByName(String name )
    { 
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":User_Name", new AttributeValue().withS(name));
		DynamoDBScanExpression scanRequest = new DynamoDBScanExpression()	 
													.withFilterExpression("User_Name = :User_Name")
													.withExpressionAttributeValues(eav); 
		if(dynamoDBMapper.scan(EndUser.class, scanRequest).size() ==0) {
			System.out.println("No user was found. Return an empty user");
			return new EndUser();
		}
    	return dynamoDBMapper.scan(EndUser.class, scanRequest).get(0);
    } 
	public Item getItemInUserOrder (String userEmail, String itemName, String kitchenName) {
		EndUser user = this.findByEmail(userEmail);
		ArrayList<Item> items = user.getTemporaryOrder().getItems();
		for(Item i : items) {
			if(i.equals(itemService.getItemByItemNameAndKitchenName(itemName, kitchenName))) {
				return i;
			}
		}
		System.out.println("There is no Item in your order");
		return new Item();
	}
	public int getIndexOfItemInOrder(String userEmail, String itemName, String kitchenName) {
		EndUser user = this.findByEmail(userEmail);
		ArrayList<Item> items = user.getTemporaryOrder().getItems();
		for(int i = 0; i < items.size();i++) {
			if(items.get(i).equals(itemService.getItemByItemNameAndKitchenName(itemName, kitchenName))) {
				return i;
			}
		}
		System.out.println("No Item found in your order");
		return -1;
	}
	public void deleteItemInUserOrder(String itemName, String kitchenName,String userEmail) {
		EndUser user = this.findByEmail(userEmail);
		ArrayList<Item> items = user.getTemporaryOrder().getItems();
		items.remove(getItemInUserOrder(userEmail, itemName, kitchenName));
		user.getTemporaryOrder().setItems(items);
		dynamoDBMapper.save(user);
	}
}
