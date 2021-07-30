package com.minhle;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;

import com.minhle.controller.KitchenUserRegistrationDto;
import com.minhle.model.kitchen.Item;
import com.minhle.model.kitchen.Kitchen;
import com.minhle.model.user.KitchenProviderUser;
import com.minhle.repo.kitchen.KitchenRepo;
import com.minhle.repo.user.KitchenProviderRepository;
import com.minhle.service.KitchenProviderService;
import com.minhle.service.NotificationEmailService;
 
@SpringBootTest
class KitchenProviderModuleApplicationTests {

	@Test
	void contextLoads() {
	}
//	@Autowired
//	private KitchenRepo kitchenRepo;
	@Autowired
	private KitchenProviderService service;
	@Autowired
	KitchenProviderRepository repo;
//	@Test
//	void testSaveProvider() {
//		KitchenProviderUser user = new KitchenProviderUser();
//		 
//		user.setEmail("minhbac444@gmail.com");
//		user.setPassword("1234");
//		user.setName("minh");
//		service.saveProvider(user);
//		
//	}
	
	@Autowired
	  private NotificationEmailService notificationService;

	  @Test
	  void testSendMail() {
	      SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
	      simpleMailMessage.setFrom("minhbac333studyus@gmail.com");
	      simpleMailMessage.setTo("minhbac333@gmail.com");
	      simpleMailMessage.setSubject("test subject");
	      simpleMailMessage.setText("test text"); 
	      notificationService.sendMailMessage(simpleMailMessage);
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
//	@Test
//	void testGetUserEmail( ) {
//		KitchenProviderUser result = repo.findByEmail("minhbac33@gmail.com");
//		 
//			System.out.println(result);
//		 
//	}
//	@Test
//	void testGetUserName( ) {
//		KitchenProviderUser result = repo.findByName("minh");
//		 
//			System.out.println(result);
//		 
//	}


}
