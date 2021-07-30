package com.minhle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhle.model.kitchen.Item;
import com.minhle.repo.kitchen.ItemRepo;
 

@Service
public class ItemService {

	
	@Autowired
	ItemRepo irepo;
	
	
	 public List<Item>	  ListAllItem(){
		 
	return	 irepo.ListAllItem();
	 } 
	 public  Item getbyname(String name) {
		return irepo.getonebyname(name);
		 
	 }  
	 public  void saveItem(Item i) {
		 irepo.saveItem(i);
		 
	 } 
 
}
