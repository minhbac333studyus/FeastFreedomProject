package com.minhle;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.minhle.controller.KitchenUserRegistrationDto;
import com.minhle.model.kitchen.Item;
import com.minhle.model.kitchen.Kitchen;
import com.minhle.model.user.KitchenProviderUser;
import com.minhle.repo.kitchen.KitchenRepo;
import com.minhle.repo.user.KitchenProviderRepository;
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
	@Autowired
	KitchenProviderRepository repo;
//	@Test
//	void testSaveProvider() {
//		KitchenProviderUser user = new KitchenProviderUser();
//		 
//		user.setEmail("minhbac55@gmail.com");
//		user.setPassword("1234");
//		user.setName("minh");
//		service.saveProvider(user);
//		
//	}
	@Autowired
	KitchenRepo kRepo;
	@Test
	void TestKitchen() {
		 
			HashSet<String> menu = new HashSet<String>();
			Item i1 = new Item();
			i1.setName("chieck fried");
			i1.setPrice(12.0);
			i1.setVegOption(true);
			menu.add(i1.toString());
		Kitchen k = new Kitchen("NOTEAM", "12-12-2021", "1Am", "2Am", "https://s3.us-east-2.amazonaws.com/feast.freedom/pic1.jpg", menu); 
 
		kRepo.saveKitchen(k); 
	}
	
//	@Autowired
//	  private NotificationEmailService notificationService;
//
//	  @Test
//	  void testSendMail() {
//	      SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//	      simpleMailMessage.setFrom("minhbac333studyus@gmail.com");
//	      simpleMailMessage.setTo("minhbac333@gmail.com");
//	      simpleMailMessage.setSubject("test subject");
//	      simpleMailMessage.setText("test text"); 
//	      notificationService.sendMailMessage(simpleMailMessage);
//	  }
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
