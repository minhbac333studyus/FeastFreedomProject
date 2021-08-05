package com.minhle.repo.kitchen;
 
import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.minhle.model.kitchen.Item;
import com.minhle.model.kitchen.Order;
import com.minhle.model.user.EndUser;
import com.minhle.service.EndUserService;
import com.minhle.service.KitchenService;

@Repository
public class OrderRepo {
	@Autowired 
	DynamoDBMapper dynamoDBMapper;
	@Autowired
	KitchenService kitService;
	@Autowired
	EndUserService uService;
	 
	public Order getTempOrder(String userEmail) {
		// TODO Auto-generated method stub
		Order tempOrder = uService.findByEmail(userEmail).getTemporaryOrder();
		return tempOrder;
	}
	@Autowired
	ItemRepo itemRepo;
 
	public void addItemToCart(String kitchenName, String userEmail, String itemName) { 
		EndUser a = uService.findByEmail(userEmail);
		Order order = this.getTempOrder(userEmail);  
		Double tempTotal = order.getTotal();
		Item i = itemRepo.getItemByItemNameAndKitchenName(itemName, kitchenName);
		order.getItems().add(i );
		tempTotal += i.getPrice();
		order.setTotal(tempTotal);  
		
		a.setTemporaryOrder(order);
		uService.saveUser(a);
	}
 
}
 