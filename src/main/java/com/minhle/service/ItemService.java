package com.minhle.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.minhle.model.kitchen.Item;
import com.minhle.model.kitchen.Kitchen;
import com.minhle.repo.kitchen.ItemRepo;
import com.minhle.repo.kitchen.KitchenRepo;
 

@Service
public class ItemService {

	
	@Autowired
	ItemRepo irepo;
	  
	@Autowired KitchenRepo krepo;
	
	 
	  
 
}
