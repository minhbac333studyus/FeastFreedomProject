package com.minhle.service;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhle.model.kitchen.Cart;
import com.minhle.model.kitchen.Item;
import com.minhle.repo.kitchen.CartRepo;
import com.minhle.repo.kitchen.KitchenRepo;

@Service
public class CartService {
	
	@Autowired
	CartRepo crepo;
	@Autowired
	KitchenRepo kitchenRepo; 
//	public void addItemToCart(String ItemName, String KitchenName) {
//		Set<String> allItem = kitchenRepo.getbykitcheName(KitchenName).getMenu();
//		for(String i  : allItem) {
//			if(i.getName() == ItemName) {
//				
//			}
//		}
//	}
	public void addedtocart(Cart c) {
		
		crepo.addedtocart(c);
	}
	
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
	
	
	
	public void deleteitem(Integer d,Cart c) {
		crepo.deleteitem(d, c);
	}
}
