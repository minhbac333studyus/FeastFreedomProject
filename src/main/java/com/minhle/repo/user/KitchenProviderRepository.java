package com.minhle.repo.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.minhle.model.user.KitchenProviderUser;
 
@Repository
public class  KitchenProviderRepository  {
//	 @Autowired
//	 private DynamoDBMapper dynamoDBMapper;
//	 public  void saveUser(KitchenProviderUser user) {
//		 dynamoDBMapper.save(user);
//	 }
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
    	return dynamoDBMapper.load(KitchenProviderUser.class, email);
    }

}
