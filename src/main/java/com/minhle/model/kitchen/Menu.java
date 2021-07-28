package com.minhle.model.kitchen;

import java.util.List;
 
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Menu { 
	@DynamoDBAttribute
	 private List<Item> Menu;
}
