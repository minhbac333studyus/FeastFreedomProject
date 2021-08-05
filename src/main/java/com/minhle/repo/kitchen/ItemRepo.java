package com.minhle.repo.kitchen; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository; 
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.minhle.model.kitchen.Item;
import com.minhle.model.kitchen.Kitchen;
import com.minhle.service.KitchenService; 
@Repository
public class ItemRepo { 
	@Autowired
    private DynamoDBMapper dynamoDBMapper; 
	@Autowired
	private KitchenService kitchenService;
	public void saveItem(Item i){ 
		dynamoDBMapper.save(i);
	} 
	public Item getItemByItemNameAndKitchenName(String ItemName, String KitchenName) { 
		ArrayList<Item> menu = kitchenService.getKitchenMenu(KitchenName);
		for(Item i : menu) {
			if(i.getName().equals(ItemName)) {
				return i;
			}
		}
        System.out.println("Can not find the Item you need in KitchenName");
        return new Item(); 
	}
	
	public List<Item> ListAllItemInKitchen(String KitchenName){ 
		  return kitchenService.getKitchenMenu(KitchenName);
	 }   
	public void deleteItemInMenu(String ItemName,String KitchenName) {
		List<Item> Menu = kitchenService.getKitchenMenu(KitchenName);
		Menu.remove(getItemByItemNameAndKitchenName(ItemName, KitchenName));
		Kitchen k = kitchenService.getbykitcheName(KitchenName);
		k.setMenu((ArrayList<Item>)Menu);
		kitchenService.saveKitchen(k);
	}
	public void updateItemInMenu(Item Item, String ItemName , String kitchenName) {
		List<Item> Menu = kitchenService.getKitchenMenu(kitchenName);
		int IndexOfItemNeedReplace = Menu.indexOf(kitchenService.getSelectedItemFromKitchen(kitchenName, ItemName));
		Menu.set(IndexOfItemNeedReplace, Item); 
		Kitchen k = kitchenService.getbykitcheName(kitchenName);
		k.setMenu((ArrayList<Item>)Menu);
		kitchenService.saveKitchen(k);
	} 
}

