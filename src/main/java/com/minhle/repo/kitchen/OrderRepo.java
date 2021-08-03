package com.minhle.repo.kitchen;
 
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper; 
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
	public Order orderUnconverted(String orderUnconvert) {
		Order order = new Order() ;
		HashSet<String> items =  new HashSet<String>();
		if(orderUnconvert != null && orderUnconvert.length() != 0) {
			String[] data = orderUnconvert.split("/");
			String listItemUnconvert = data[0].trim().substring(1, data[0].trim().length() - 1 );
			String[] itemsUnconvert = listItemUnconvert.split(",");
			for(String i: itemsUnconvert) {
				items.add(i);
			}
			
			String email = data[1].trim();
			Double total= Double.parseDouble(data[2].trim());
			order.setItems(items);
		 
			order.setUserEmail(email);
			order.setTotal(total);
			return order;
		}
		return order;
	}
	public String getTempOrder(String userEmail) {
		// TODO Auto-generated method stub
		String tempOrder = uService.findByEmail(userEmail).getTemporaryOrder();
		return tempOrder;
	}
	@Autowired
	ItemRepo itemRepo;
 
	public void addItemToOrder(String kitchenName, String userEmail, String itemName) {
		EndUser a = uService.findByEmail(userEmail);
		String tempOrder = this.getTempOrder(userEmail);
		Order order = this.orderUnconverted(tempOrder);
		 Double tempTotal = this.orderUnconverted(a.getTemporaryOrder()).getTotal();
		 String i = itemRepo.getItemByItemNameAndKitchenName(itemName, kitchenName);
		order.getItems().add(i.toString());
		tempTotal += itemRepo.unConvert(i).getPrice();
		order.setTotal(tempTotal);  
		
		a.setTemporaryOrder(order.toString());
		uService.saveUser(a);
	}
 
}
 