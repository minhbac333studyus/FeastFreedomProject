package com.minhle.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhle.model.kitchen.Item;
import com.minhle.model.kitchen.Kitchen;
import com.minhle.repo.kitchen.KitchenRepo;
 
@Service
public class KitchenService {

	
	@Autowired
	private KitchenRepo krepo; 
	@Autowired
	private ItemService iService;
	public List<Kitchen>ListAllKitchen(){  
		List<Kitchen>klist =  (List<Kitchen>) krepo.ListAllKitchen();
		return klist;
	}
    public List<Kitchen>ListAllKitchenByProviderEmail(String ProviderName){
    	return krepo.getAllKitchenByProviderEmail(ProviderName);
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
	public void deleteKitchenByKitchenName(String kitchenName) {
		krepo.deleteKitchenByKitchenName(kitchenName);
	}
	public HashSet<Item> getKitchenMenu(String kitchenName){
		Kitchen result = krepo.getbykitcheName(kitchenName);
		HashSet<String>allItemUnconverted = result.getMenu(); 
		HashSet<Item> allItemConverted = new HashSet<>();;
		 
		for(String iUn : allItemUnconverted) {
			allItemConverted.add(iService.unconvertItem(iUn));
		}
		return allItemConverted; 
	}
	public Item getSelectedItemFromKitchen(String kitchenName, String ItemName) {
		HashSet<Item> menu = getKitchenMenu(kitchenName);
		Item item = new Item();
		 
		for(Item i  : menu) {
			if(i.getName() ==  ItemName) {
				return i;  
			}
		}
		return item;
	}
}
