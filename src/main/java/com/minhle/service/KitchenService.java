package com.minhle.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhle.model.kitchen.Item;
import com.minhle.model.kitchen.Kitchen;
import com.minhle.repo.kitchen.ItemRepo;
import com.minhle.repo.kitchen.KitchenRepo;
 
@Service
public class KitchenService {

	
	@Autowired
	private KitchenRepo krepo; 
	@Autowired
	private ItemRepo iService;
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
	public ArrayList<Item> getKitchenMenu(String kitchenName){
		return krepo.getbykitcheName(kitchenName).getMenu();
		 
		 
	}
	public void addItemToKitchenMenu(String kitchenName, Item item) { 
		krepo.addItemToKitchenMenu(kitchenName, item);
	}
	public void updateMenu(String kitchenName,String itemName, Item item) {
		iService.updateItemInMenu(item, itemName, kitchenName);
	}
	
	public Item getSelectedItemFromKitchen(String kitchenName, String ItemName) {
		ArrayList<Item> menu = getKitchenMenu(kitchenName);
		System.out.println(menu.toString());
		for(Item i  : menu) {
			if(i.getName().equals(ItemName)) {
				return i;  
			}
		}
		return new Item();
	}
}
