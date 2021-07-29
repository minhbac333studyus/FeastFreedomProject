package com.minhle.model.user;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

public class RoleConverter implements DynamoDBTypeConverter<String, Role> {
	  
    	@Override
    	public String convert (Role role) {
    		return role.getName();
    	}

    	@Override
    	public Role unconvert(String object) {
    		// TODO Auto-generated method stub
    		return new Role(object);
    	}
 
}
