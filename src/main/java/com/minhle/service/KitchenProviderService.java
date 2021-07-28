package com.minhle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.minhle.model.KitchenProviderUser;
import com.minhle.repo.KitchenProviderRepository;

@Service
public class KitchenProviderService {
	@Autowired
	KitchenProviderRepository kitchenProviderRepository;

	public void saveProvider(KitchenProviderUser kitchenProviderUser) {
		kitchenProviderRepository.saveUser(kitchenProviderUser);
	}
	public List<KitchenProviderUser> findAllProvider() {
		return kitchenProviderRepository.findAllUsers();
	}
}
