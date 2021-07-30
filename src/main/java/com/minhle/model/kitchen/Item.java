package com.minhle.model.kitchen;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute; 
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument; 
@DynamoDBDocument
public class Item {  
	 @DynamoDBAttribute(attributeName="itemname")
	 private String name;
	 @DynamoDBAttribute
	 private boolean vegOption;
	 @DynamoDBAttribute
	 private Double price; 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVegOption() {
		return vegOption;
	}

	public void setVegOption(boolean vegOption) {
		this.vegOption = vegOption;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", vegOption=" + vegOption + ", price=" + price + "]";
	}
	 
	 
	 
	 
	 

}
