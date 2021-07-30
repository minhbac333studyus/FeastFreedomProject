package com.minhle.repo.user; 
import java.util.HashMap;
import java.util.List;import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository; 
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.minhle.model.user.KitchenProviderUser;
import com.minhle.model.user.EndUser;
 
@Repository
public class  UserRepository  { 
	final String TableName = "KitchenProviderUser";
	@Autowired
    private DynamoDBMapper dynamoDBMapper; 
    public EndUser saveUser(EndUser user) {
        dynamoDBMapper.save(user);
        return user;
    }
    public List<EndUser> findAllUsers(){
    	//DynamoDBQueryExpression<KitchenProviderUser> queryExpression = new DynamoDBQueryExpression<KitchenProviderUser>();
    	DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
    	return dynamoDBMapper.scan(EndUser.class, scanExpression);
    } 
	public EndUser findByEmail(String email )
    { 
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":Email", new AttributeValue().withS(email));
		DynamoDBScanExpression scanRequest = new DynamoDBScanExpression()	 
													.withFilterExpression("Email = :Email")
													.withExpressionAttributeValues(eav); 
		if(dynamoDBMapper.scan(KitchenProviderUser.class, scanRequest).size() ==0) {
			return new EndUser();
		}
    	return dynamoDBMapper.scan(EndUser.class, scanRequest).get(0);
    } 
	
	public EndUser findByName(String name )
    { 
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":Service_Provider_Name", new AttributeValue().withS(name));
		DynamoDBScanExpression scanRequest = new DynamoDBScanExpression()	 
													.withFilterExpression("Service_Provider_Name = :Service_Provider_Name")
													.withExpressionAttributeValues(eav); 
		if(dynamoDBMapper.scan(KitchenProviderUser.class, scanRequest).size() ==0) {
			return new EndUser();
		}
    	return dynamoDBMapper.scan(EndUser.class, scanRequest).get(0);
    } 
}
