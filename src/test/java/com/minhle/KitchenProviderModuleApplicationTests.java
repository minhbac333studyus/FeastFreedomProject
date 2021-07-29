package com.minhle;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.minhle.model.kitchen.Item;
import com.minhle.model.kitchen.Kitchen;
import com.minhle.model.user.KitchenProviderUser;
import com.minhle.repo.kitchen.KitchenRepo;
import com.minhle.service.KitchenProviderService;
 
@SpringBootTest
class KitchenProviderModuleApplicationTests {

	@Test
	void contextLoads() {
	}
//	@Autowired
//	private KitchenRepo kitchenRepo;
	@Autowired
	private KitchenProviderService service;
	@Test
	void testSaveProvider() {
		KitchenProviderUser user = new KitchenProviderUser();
		user.setEmail("test3timesl@gma.lc");
		user.setPassword("LOL");
		user.setName("KKK");
		service.saveProvider(user);
		
	}
//	@Test
//	void testGetAllProvider() {
//		System.out.println("LOL");
//		List<KitchenProviderUser> result = service.findAllProvider();
//		for( KitchenProviderUser u : result) {
//			System.out.println(u);
//		}
//	}
//	@Test
//	void testSaveKitchen() {
//		Kitchen kit = new Kitchen();
//		List<Item> me = new ArrayList<>();
//		me.add(new Item("CARROT", false, 12.0));
//		kit.setImageUrl("this url");
//		kitchenRepo.saveKitchen(kit);
//	}
	

}
