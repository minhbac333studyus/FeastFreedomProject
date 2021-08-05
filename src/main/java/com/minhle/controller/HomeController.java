package com.minhle.controller;

import java.util.ArrayList;
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
import com.minhle.repo.kitchen.ItemRepo;
import com.minhle.repo.kitchen.OrderRepo;
import com.minhle.service.KitchenProviderService;
import com.minhle.service.KitchenService;

@Controller("/home")
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
	@GetMapping("/403")
	public String errorHandle( ) {
		return "redirect:/";
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
}
