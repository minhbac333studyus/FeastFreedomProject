package com.minhle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.minhle.model.KitchenProviderUser;
import com.minhle.repo.KitchenProviderRepository;
import com.minhle.service.KitchenProviderService;
 
@SpringBootTest
class KitchenProviderModuleApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private KitchenProviderService service;
	@Test
	void testSaveProvider() {
		KitchenProviderUser user = new KitchenProviderUser();
		user.setEmail("test2timesl@gma.lc");
		user.setPassword("LOL");
		service.saveProvider(user);
		
	}

}
