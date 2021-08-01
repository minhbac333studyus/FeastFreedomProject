package com.minhle.repo.kitchen; 
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
	public void    saveItem(Item i){ 
		dynamoDBMapper.save(i);
	} 
	public Item getItemByItemNameAndKitchenName(String ItemUnconvert, String KitchenName) {
		Kitchen k = kitchenService.getbykitcheName(KitchenName);
		Set<String>menu = k.getMenu();
		if(menu.size() ==0)
			return new Item();  
		Iterator<String> i = menu.iterator();
        while (i.hasNext()) {  
        	if(ItemUnconvert == i.next()) {
        		return this.unConvert(ItemUnconvert);
        	}
        }
        System.out.println("Can not find the Item you need in KitchenName");
        return new Item();
		
	}
	public Item unConvert(String ItemUnconvert) {
		Item item = new Item();
		try {
            if (ItemUnconvert != null && ItemUnconvert.length() != 0) {
                String[] data = ItemUnconvert.split(",");
                item.setName(data[0].trim());
                if (data[1].trim() == "0" || data[1].trim() == "false") {
                	item.setVegOption(false);
                }
                else {
                	item.setVegOption(true);
                }
                item.setPrice(Double.parseDouble(data[2].trim()));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
		return item;  
	}
	public List<Item> ListAllItem(){ 
		 DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
	    	return dynamoDBMapper.scan(Item.class, scanExpression);
		  
	 }  
	 public  String updateItem(String id,Item i) { 
		 dynamoDBMapper.save(i,
				 			 new DynamoDBSaveExpression()
				 			 		.withExpectedEntry("itemid",
				 			 						   new ExpectedAttributeValue(new AttributeValue().withS(id)
		 			 								   )));
        return id; 
	 } 
}

