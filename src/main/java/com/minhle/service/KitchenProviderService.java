package com.minhle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhle.model.user.KitchenProviderUser;
import com.minhle.repo.user.KitchenProviderUserRepository;

@Service
public class KitchenProviderService {
	@Autowired
	KitchenProviderUserRepository kitchenProviderRepository;

	public void saveProvider(KitchenProviderUser kitchenProviderUser) {
		kitchenProviderRepository.saveUser(kitchenProviderUser);
	}
	public List<KitchenProviderUser> findAllProvider() {
		return kitchenProviderRepository.findAllUsers();
	}
}
