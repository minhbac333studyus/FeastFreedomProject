package com.minhle.controller; 
import java.util.ArrayList; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import com.minhle.model.kitchen.Item;
import com.minhle.model.kitchen.Order;
import com.minhle.repo.kitchen.ItemRepo;
import com.minhle.repo.kitchen.OrderRepo;
import com.minhle.repo.user.EndUserRepository; 
@Controller("/userSide")
public class UserController {
	@Autowired
	OrderRepo orderRepo;
	@Autowired
	ItemRepo itemRepo;
	@Autowired
	EndUserRepository endUserService;
	@GetMapping("/user/cart/{userEmail}")
	public String displayCart(@PathVariable String userEmail, Model model) {
		Order tempOrder = orderRepo.getTempOrder(userEmail); 
		ArrayList<Item> listItem = tempOrder.getItems(); 
		model.addAttribute("allItem", listItem);
		model.addAttribute("tempOrder", tempOrder);
		return "cart";
	}
	@GetMapping("/cart/add/{userEmail}/{kitchenName}/{itemName}")
	public String addToCart(@PathVariable String userEmail
			,@PathVariable String kitchenName
			,@PathVariable String itemName,
			 Model model
			)
	{
		orderRepo.addItemToCart(kitchenName, userEmail, itemName);
		return"redirect:/user/cart/{userEmail}";
	} 
	@GetMapping("/cart/delete/{userEmail}/{kitchenName}/{itemName}")
	public String deleteItemFromCart(@PathVariable String  itemName,@PathVariable String kitchenName,@PathVariable String userEmail) {
		endUserService.deleteItemInUserOrder(itemName, kitchenName, userEmail);
		return "redirect:/user/cart/{userEmail}";
	} 
}
