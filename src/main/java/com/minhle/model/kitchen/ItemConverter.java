package com.minhle.model.kitchen;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

public class ItemConverter implements DynamoDBTypeConverter<String, Item> {

	@Override
	public String convert(Item object) {
		// TODO Auto-generated method stub
		Item item = (Item)object;
		String itemString = "";
		try {
			if (item.getName() != "empty")
			{
				itemString = String.format("%s x %s x %s", item.getName(), item.isVegOption(), item.getPrice());
			}
		} catch (Exception e) {
            e.printStackTrace();
        }
		return itemString;
	}

	@Override
	public Item unconvert(String s) {
		// TODO Auto-generated method stub
		Item item = new Item();
		try {
            if (s != null && s.length() != 0) {
                String[] data = s.split("x");
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

}
