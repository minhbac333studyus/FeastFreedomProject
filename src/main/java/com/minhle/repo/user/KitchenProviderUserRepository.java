package com.minhle.repo.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper; 
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.minhle.model.user.KitchenProviderUser;
import com.minhle.model.user.KitchenProviderUserPrincipal;

@Repository
public class  KitchenProviderUserRepository  {
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
    public KitchenProviderUser findByEmail(String email )
    {
    	return dynamoDBMapper.load(KitchenProviderUser.class, email);
    }
    public List<KitchenProviderUser> findAllUsers(){
    	//DynamoDBQueryExpression<KitchenProviderUser> queryExpression = new DynamoDBQueryExpression<KitchenProviderUser>();
    	DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
    	return dynamoDBMapper.scan(KitchenProviderUser.class, scanExpression);
    }

}
