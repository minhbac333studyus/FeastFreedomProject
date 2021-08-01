package com.minhle.service;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhle.model.kitchen.Cart; 
import com.minhle.repo.kitchen.CartRepo;
import com.minhle.repo.kitchen.ItemRepo;
import com.minhle.repo.kitchen.KitchenRepo;

@Service
public class CartService {
	
	@Autowired
	CartRepo crepo;
	@Autowired
	KitchenRepo kitchenRepo; 
	@Autowired
	ItemRepo itemRepo;
//	public void addItemToCart(String ItemUnconverted, String KitchenName) {
//		 Item newItem = itemRepo.getItemByItemNameAndKitchenName(ItemUnconverted, KitchenName);
//		 crepo.addedtocart(null);
//	}
	 
	
	public Cart getcartbyname(String name) {
		
		return crepo.getcartbyname(name);
	}

	
	public double totalprice(Cart c) {
		double total =0;
		String str = c.getItems().toString();
		
		Pattern p = Pattern.compile("(\\d+(?:\\.\\d+))");
		Matcher m = p.matcher(str);
		while(m.find()) {
		    double d = Double.parseDouble(m.group(1));
		    total+=d;
		} 
		return total; 
	}
	
	
 
}
