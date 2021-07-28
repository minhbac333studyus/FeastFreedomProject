package com.minhle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.minhle.model.KitchenProviderUser;
import com.minhle.repo.KitchenProviderRepository;

@Service
public class KitchenProviderService {
	@Autowired
	KitchenProviderRepository kitchenProviderRepository;

	public void saveProvider(KitchenProviderUser kitchenProviderUser) {
		kitchenProviderRepository.saveUser(kitchenProviderUser);
	}
}
