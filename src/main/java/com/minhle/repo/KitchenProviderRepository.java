package com.minhle.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.minhle.model.KitchenProviderUser;

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

}
