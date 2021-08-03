package com.minhle.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.minhle.model.kitchen.Item;
import com.minhle.model.kitchen.Kitchen;
import com.minhle.model.kitchen.Order;
import com.minhle.model.user.KitchenProviderUser;
import com.minhle.repo.kitchen.OrderRepo;
import com.minhle.service.KitchenProviderService;
import com.minhle.service.KitchenService;

@Controller
public class HomeController {

	@Autowired
	KitchenProviderService kitchenProviderService;
	@Autowired
	KitchenService kitchenService;
	@GetMapping("/")
	public String root(Model model)
	{
		List<Kitchen> kitchenList = kitchenService.ListAllKitchen();
		model.addAttribute("kitchenList", kitchenList); 
		return "homepage"; //acess index.html
	}
	@GetMapping("/login")
    public String loginAsProvider(Model model) {
        return "login";  
	}
	@GetMapping("/homepage/loginsucess")
	public String rootAfterLogin(Model model)
	{
		List<Kitchen> kitchenList = kitchenService.ListAllKitchen();
		model.addAttribute("kitchenList", kitchenList); 
		return "homepage_after_login";
	}

	 
	@RequestMapping("/kitchenProviderList")
	public String getKitchenProviderList(Model model) {
		List<KitchenProviderUser> kitchenProviders = kitchenProviderService.findAllProvider();
		model.addAttribute("kitchenProviders", kitchenProviders);
		return "list_provider_user";
	}
	
	@RequestMapping("/provider/kitchenList")
	public String getKitchenList(Model model, Authentication authentication) {
		System.out.println(authentication.getName());
		List<Kitchen> kitchenList = kitchenService.ListAllKitchenByProviderEmail(authentication.getName());
		model.addAttribute("kitchenList", kitchenList);
		return "list_kitchen_for_provider";
	}
	@RequestMapping("/provider/kitchen/save")
	public String addKitchen(Kitchen kitchen, BindingResult result, Model model) {
		if(result.hasErrors()){
			return "create_kitchen";
		}
		kitchenService.saveKitchen(kitchen);
		return "redirect:/provider/kitchenList";
	}
	@RequestMapping("/provider/kitchen/create")
	public ModelAndView showAddNewKitchenPage( ) {
		ModelAndView modelAndView = new ModelAndView("create_kitchen");
		Kitchen kitchen = new Kitchen();
		modelAndView.addObject("kitchen", kitchen);
		return modelAndView;
		
	}
	@GetMapping("/homepage/kitchen/allItem/{kitchenName}")
	public String  displayMenu(@PathVariable String kitchenName, Model model) { 
		 
		 
		HashSet<Item> listMenu = kitchenService.getKitchenMenu(kitchenName);
		model.addAttribute("menuList",listMenu);
		return "all_Menu_Item";
	}
	@Autowired
	OrderRepo orderRepo;
	@GetMapping("/user/cart/{userEmail}")
	public String displayCart(@PathVariable String userEmail, Model model) {
		String tempOrder = orderRepo.getTempOrder(userEmail);
		Order convertOrder = orderRepo.orderUnconverted(tempOrder);
		model.addAttribute("tempOrder", convertOrder);
		return "cart";
	}
	@GetMapping("/cart/add/{userEmail}/{kitchenName}/{itemName}")
	public String addToCart(@PathVariable String userEmail
			,@PathVariable String kitchenName
			,@PathVariable String itemName,
			 Model model
			)
	{
		orderRepo.addItemToOrder(kitchenName, userEmail, itemName);
		return"redirect:/user/cart/{userEmail}";
	}
	
 
//	@RequestMapping("/provider/delete/{kitchenNamePathVar}")
//	public String deleteKitchen(@PathVariable (name ="kitchenNamePathVar" ) String name) {
//		kitchenService.deleteKitchenByKitchenName(name);
//		return "redirect:/provider/kitchenList";
//		
//	}
}
