//package com.minhle;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.minhle.config.security.KitchenUserRegistrationDto;
//import com.minhle.model.kitchen.Cart;
//import com.minhle.model.kitchen.Item; 
//import com.minhle.model.kitchen.Kitchen;
//import com.minhle.model.user.EndUser;
//import com.minhle.model.user.KitchenProviderUser;
//import com.minhle.repo.kitchen.CartRepo;
//import com.minhle.repo.kitchen.ItemRepo;
//import com.minhle.repo.kitchen.KitchenRepo;
//import com.minhle.repo.user.EndUserRepository;
//import com.minhle.repo.user.KitchenProviderRepository;
//import com.minhle.service.KitchenProviderService;
//import com.minhle.service.KitchenService;
// 
//@SpringBootTest
//class KitchenProviderModuleApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}
////	@Autowired
////	private KitchenRepo kitchenRepo;
//	@Autowired
//	private KitchenProviderService service;
//	@Autowired
//	KitchenProviderRepository repo;
//	@Autowired
//	KitchenService kitchenService;
//	@Autowired
//	KitchenRepo kitchenRepo;
//	@Autowired
//	EndUserRepository endUserRepo;
//	@Autowired
//	ItemRepo itemRepo;
//	@Autowired
//	CartRepo cartRepo;
////	@Test
////	void testSaveProvider() {
////		KitchenProviderUser user = new KitchenProviderUser();
////		 
////		user.setEmail("minhbac55@gmail.com");
////		user.setPassword("1234");
////		user.setName("minh");
//////		List<Role> roles = new ArrayList<>();
//////		roles.add(new Role("roles_provider"));
//////		user.getRoles().addAll(roles);
////		service.saveProvider(user); 
////	}
//	@Autowired
//	KitchenRepo kRepo;
////	 
////	@Test
////	void getItemInKitchen() {
////		String kitchenName= "NoKidding";
////		HashSet<String> items = kitchenRepo.getbykitcheName(kitchenName).getMenu();
////		Iterator<String> i = items.iterator();
////        while (i.hasNext()) { 
////        	Item  a = itemRepo.getItemByItemName(i.next());
////        	System.out.println(a.getName());
////        }
////	}
////	@Test
////	void testCart() {
////		Item item = new Item("Chicken",true,12.0);
////		List<String> allItem = new ArrayList<>();
////		allItem.add(item.toString());
////		Cart cart = new Cart("Minh",allItem);
////		cartRepo.saveCart(cart);
////		
////	}
////	@Test
////	void testGetEndUser() {
////		List<EndUser> l1 = endUserRepo.findAllUsers();
////		for(EndUser u : l1) {
////			System.out.println(u);
////		}
////	}
////	@Test
////	void TestCreateEndUser() {
////		EndUser u1 = new EndUser("minh","minhbac333@gmail.com","123");
////		endUserRepo.saveUser(u1);
////	}
////	@Test
////	void TestKitchen() { 
////			HashSet<String> menu = new HashSet<String>();
////			Item i1 = new Item();
////			i1.setName("chieck fried");
////			i1.setPrice(12.0);
////			i1.setVegOption(true);
////			menu.add(i1.toString() );
////			
////		Kitchen k = new Kitchen("p2@gmail.com","NoKidding", "11-02-2021", "0Am", "12Am", 
////									"https://s3.us-east-2.amazonaws.com/feast.freedom/pic2.jpg", menu); 
////  
////		kitchenRepo.saveKitchen(k); 
////	}
////	
////	@Test void testGetAllKitchenByKitchenName() {
////		String kitchenName= "NoKidding";
////		System.out.println(kitchenRepo.getbykitcheName(kitchenName));
////		
////				
////	}
////	@Test
////	void testGetAllKitchenByProviderName() {
////		String providerName = "minhbac333studyus@gmail.com";
////		List<Kitchen> list = kitchenRepo.getAllKitchenByProviderEmail(providerName);
////		for( Kitchen u : list) {
////			System.out.println(u);
////		}
////	}
////	@Autowired
////	  private NotificationEmailService notificationService;
////
////	  @Test
////	  void testSendMail() {
////	      SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
////	      simpleMailMessage.setFrom("minhbac333studyus@gmail.com");
////	      simpleMailMessage.setTo("minhbac333@gmail.com");
////	      simpleMailMessage.setSubject("test subject");
////	      simpleMailMessage.setText("test text"); 
////	      notificationService.sendMailMessage(simpleMailMessage);
////	  }
////	@Test
////	void testGetAllProvider() {
////		System.out.println("LOL");
////		List<KitchenProviderUser> result = service.findAllProvider();
////		for( KitchenProviderUser u : result) {
////			System.out.println(u);
////		}
////	}
////	@Test
////	void testSaveKitchen() {
////		Kitchen kit = new Kitchen();
////		List<Item> me = new ArrayList<>();
////		me.add(new Item("CARROT", false, 12.0));
////		kit.setImageUrl("this url");
////		kitchenRepo.saveKitchen(kit);
////	}
////	@Test
////	void testGetUserEmail( ) {
////		KitchenProviderUser result = repo.findByEmail("minhbac33@gmail.com");
////		 
////			System.out.println(result);
////		 
////	}
////	@Test
////	void testGetUserName( ) {
////		KitchenProviderUser result = repo.findByName("minh");
////		 
////			System.out.println(result);
////		 
////	}
//
//
//}
