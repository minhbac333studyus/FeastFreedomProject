package com.minhle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhle.model.kitchen.Kitchen;
import com.minhle.repo.kitchen.KitchenRepo;
 
@Service
public class KitchenService {

	
	@Autowired
	private KitchenRepo krepo;
	
	
public List<Kitchen>ListAllKitchen(){
		  
		  List<Kitchen>klist =  (List<Kitchen>) krepo.ListAllKitchen();
		return klist;
		  
	  }





    public Kitchen getDetailOfOneKitchen(String id){
    	
    	return krepo.getDetailOfOneKitchen(id);
    }
    
    
    public Kitchen getbykitcheName(String name) {
    	
    	return krepo.getbykitcheName(name);
    	
    }
    
    
 public Kitchen    saveKitchen(Kitchen k){
    	 
	return  krepo.saveKitchen(k);
    	 
     }
 
 
 public void updateKitchen(Kitchen K) {
	 Kitchen b=  getDetailOfOneKitchen(K.getKitchenid());
	 krepo.updateKitchen(b.getKitchenid(), K);
		
	 
 }
}
