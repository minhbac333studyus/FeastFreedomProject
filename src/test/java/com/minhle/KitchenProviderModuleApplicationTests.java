package com.minhle;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.minhle.config.security.KitchenUserRegistrationDto; 
import com.minhle.model.kitchen.Item; 
import com.minhle.model.kitchen.Kitchen;
import com.minhle.model.kitchen.Order;
import com.minhle.model.user.EndUser;
import com.minhle.model.user.KitchenProviderUser; 
import com.minhle.repo.kitchen.ItemRepo;
import com.minhle.repo.kitchen.KitchenRepo;
import com.minhle.repo.kitchen.OrderRepo;
import com.minhle.repo.user.EndUserRepository;
import com.minhle.repo.user.KitchenProviderRepository;
import com.minhle.service.EndUserService;
import com.minhle.service.KitchenProviderService;
import com.minhle.service.KitchenService;
 
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
	@Autowired
	KitchenService kitchenService;
	@Autowired
	KitchenRepo kitchenRepo;
	@Autowired
	EndUserRepository endUserRepo;
	@Autowired
	ItemRepo itemRepo;
	 @Autowired
	 DynamoDBMapper dynamodbMapper;
	 @Autowired
	 OrderRepo orderRepo;
	 @Autowired
	 EndUserService uService;
	 @Test
	 void testOrderRepo() { 
		 String userEmail = "u2@gmail.com";
		 String itemName= "chieckFried";
		 String kitchenName = "NoKidding";
		 orderRepo.addItemToOrder(kitchenName, userEmail, itemName);
 
 }
//	@Test
//	void testSaveProvider() {
//		KitchenProviderUser user = new KitchenProviderUser();
//		 
//		user.setEmail("minhbac55@gmail.com");
//		user.setPassword("1234");
//		user.setName("minh");
////		List<Role> roles = new ArrayList<>();
////		roles.add(new Role("roles_provider"));
////		user.getRoles().addAll(roles);
//		service.saveProvider(user); 
//	}
//	@Autowired
//	KitchenRepo kRepo;
//	@Test void testGetAllItemMenu() {
//		HashSet<Item> listMenu = kitchenService.getKitchenMenu("NoKidding");
//		for(Item i : listMenu) {
//			System.out.println(i.getName());
//		}
//	}
//	 
//	@Test 
//	void testSaveKitchenWithItemArray() {
//		HashSet<String> menu = new HashSet<String>();
//		Item i1 = new Item();
//		i1.setKitchenName("NoKidding");
//		i1.setName("chieckFried");
//		i1.setPrice(12.0);
//		i1.setVegOption(true);
//		
//		Item i2 = new Item();
//		i2.setKitchenName("NoKidding");
//		i2.setName("chieckPIzza");
//		i2.setPrice(12.0);
//		i2.setVegOption(true);
//		menu.add(i2.toString() );
//		menu.add(i1.toString());
//		Order temp = new Order();
//		temp.setUserEmail("p1@gmail.com");
//		Kitchen k = new Kitchen("p1@gmail.com","NoKidding", "12-02-2022", "0Am", "12Am", 
//									"https://s3.us-east-2.amazonaws.com/feast.freedom/pic1.jpg", menu,temp.toString()); 
//		 
//		
//		kitchenRepo.saveKitchen(k ); 
//	}
//	 @Autowired
//	 OrderRepo orderRepo;
//	 @Test
//	 void addItemToOrder() {
//			Item i1 = new Item();
//		 
//			i1.setName("chieck no no");
//			i1.setPrice(12.0);
//			i1.setVegOption(false);
//		 orderRepo.addItemToOrder("u1@gmail.com", null);
//	 }
//	@Test
//	void testCart() {
//		Item item = new Item("Chicken",true,12.0);
//		List<String> allItem = new ArrayList<>();
//		allItem.add(item.toString());
//		Cart cart = new Cart("Minh",allItem);
//		cartRepo.saveCart(cart);
//		
//	}
//	@Test
//	void testGetEndUser() {
//		List<EndUser> l1 = endUserRepo.findAllUsers();
//		for(EndUser u : l1) {
//			System.out.println(u);
//		}
//	}
//	@Test
//	void TestCreateEndUser() {
//		EndUser u1 = new EndUser("minh","minhbac333@gmail.com","123");
//		endUserRepo.saveUser(u1);
//	}
//	@Test
//	void TestKitchen() { 
//			HashSet<String> menu = new HashSet<String>();
//			Item i1 = new Item();
//			i1.setName("chieck fried");
//			i1.setPrice(12.0);
//			i1.setVegOption(true);
//			
//			Item i2 = new Item();
//			i2.setName("chieck fried");
//			i2.setPrice(12.0);
//			i2.setVegOption(true);
//			menu.add(i2.toString() );
//			
//		Kitchen k = new Kitchen("p2@gmail.com","NoKidding", "11-02-2021", "0Am", "12Am", 
//									"https://s3.us-east-2.amazonaws.com/feast.freedom/pic2.jpg", menu); 
//  
//		kitchenRepo.saveKitchen(k); 
//	}
//	
//	@Test void testGetAllKitchenByKitchenName() {
//		String kitchenName= "NoKidding";
//		System.out.println(kitchenRepo.getbykitcheName(kitchenName));
//		
//				
//	}
//	@Test
//	void testGetAllKitchenByProviderName() {
//		String providerName = "minhbac333studyus@gmail.com";
//		List<Kitchen> list = kitchenRepo.getAllKitchenByProviderEmail(providerName);
//		for( Kitchen u : list) {
//			System.out.println(u);
//		}
//	}
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
