package com.minhle.repo.user; 
import java.util.HashMap;
import java.util.List;import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository; 
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.minhle.model.user.kitchenprovider.KitchenProviderUser;
 
@Repository
public class  KitchenProviderRepository  { 
	final String TableName = "KitchenProviderUser";
	@Autowired
    private DynamoDBMapper dynamoDBMapper; 
    public KitchenProviderUser saveUser(KitchenProviderUser user) {
        dynamoDBMapper.save(user);
        return user;
    }
    public List<KitchenProviderUser> findAllUsers(){
    	//DynamoDBQueryExpression<KitchenProviderUser> queryExpression = new DynamoDBQueryExpression<KitchenProviderUser>();
    	DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
    	return dynamoDBMapper.scan(KitchenProviderUser.class, scanExpression);
    } 
	public KitchenProviderUser findByEmail(String email )
    { 
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":Email", new AttributeValue().withS(email));
		DynamoDBScanExpression scanRequest = new DynamoDBScanExpression()	 
													.withFilterExpression("Email = :Email")
													.withExpressionAttributeValues(eav); 
		if(dynamoDBMapper.scan(KitchenProviderUser.class, scanRequest).size() ==0) {
			return new KitchenProviderUser();
		}
    	return dynamoDBMapper.scan(KitchenProviderUser.class, scanRequest).get(0);
    } 
	
	public KitchenProviderUser findByName(String name )
    { 
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":Service_Provider_Name", new AttributeValue().withS(name));
		DynamoDBScanExpression scanRequest = new DynamoDBScanExpression()	 
													.withFilterExpression("Service_Provider_Name = :Service_Provider_Name")
													.withExpressionAttributeValues(eav); 
		if(dynamoDBMapper.scan(KitchenProviderUser.class, scanRequest).size() ==0) {
			return new KitchenProviderUser();
		}
    	return dynamoDBMapper.scan(KitchenProviderUser.class, scanRequest).get(0);
    } 
}
