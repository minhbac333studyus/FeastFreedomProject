package com.minhle.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
import com.minhle.repo.kitchen.ItemRepo;
import com.minhle.repo.kitchen.KitchenRepo;
import com.minhle.service.KitchenService;

@Controller("/kitchenSide")
public class KitchenController {

	@Autowired KitchenService kitchenService; 
	@Autowired ItemRepo itemRepo;
	@RequestMapping("/provider/delete/{kitchenNamePathVar}")
	public String deleteKitchen(@PathVariable (name ="kitchenNamePathVar" ) String name) {
		kitchenService.deleteKitchenByKitchenName(name);
		return "redirect:/provider/kitchenList";
		
	}

	@RequestMapping("/provider/kitchen/save/{userEmail}")
	public String addKitchen(Kitchen kitchen, BindingResult result,  @PathVariable String userEmail) {
		if(result.hasErrors()){
			return "create_kitchen";
		} 
		kitchen.setProviderEmail(userEmail);
		kitchen.setMenu(new ArrayList<Item>());
		kitchenService.saveKitchen(kitchen);
		return "redirect:/provider/kitchenList";
	}
	@RequestMapping("/provider/kitchen/create/{userEmail}")
	public ModelAndView showAddNewKitchenPage( @PathVariable String userEmail) {
		ModelAndView modelAndView = new ModelAndView("create_kitchen");
		Kitchen kitchen = new Kitchen();
		modelAndView.addObject("kitchen", kitchen);
		return modelAndView; 
	}
	/*
	 * CRUD------------ MENU----------------------
	 */
	@GetMapping("/homepage/kitchen/allItem/{kitchenName}")//---------------------READ--MENU------------------//
	public String  displayMenu(@PathVariable String kitchenName, Model model) { 
		ArrayList<Item> listMenu = kitchenService.getKitchenMenu(kitchenName);	
		Kitchen kit = kitchenService.getbykitcheName(kitchenName);
		model.addAttribute("kitchen", kit);
		model.addAttribute("menuList",listMenu);
		return "all_Menu_Item";
	}
	@RequestMapping("/provider/kitchen/menu/save/{kitchenName}")//---------------------CREATE--MENU-----------------//
	public String addItemToMenu(Item item , BindingResult result ,@PathVariable String kitchenName  ) {
		if(result.hasErrors()) {
			return "create_menu"; 
		} 
		kitchenService.addItemToKitchenMenu(kitchenName, item); 
		return "redirect:/homepage/kitchen/allItem/{kitchenName}"; 
	}
	@RequestMapping("/provider/kitchen/menu/create/{kitchenName}")//---------------------CREATE----MENU----------------//
	public ModelAndView showAddNewMenuPage( Model model,@PathVariable String kitchenName) {
		ModelAndView modelAndView = new ModelAndView("create_menu");
		Item item = new Item();
		item.setKitchenName(kitchenName);
		model.addAttribute("kitchen", kitchenService.getbykitcheName(kitchenName));
		modelAndView.addObject("item", item);
		return modelAndView;
	}
	
	@RequestMapping("/provider/kitchen/menu/update/{userEmail}/{kitchenName}/{itemName}")//-----------------------UPDATE------MENU-------------------------//
	public String updateMenuForm(@PathVariable String userEmail, @PathVariable String kitchenName, @PathVariable String itemName, Model model) {
		Item item  = kitchenService.getSelectedItemFromKitchen(kitchenName, itemName);
		model.addAttribute("item", item);
		model.addAttribute("kitchen", kitchenService.getbykitcheName(kitchenName));
		return "edit_menu";
	}
	
	@RequestMapping("/provider/kitchen/menu/save/{kitchenName}/{itemName}")//---------------------Update--MENU-----------------//
	public String updateToMenu(Item item , BindingResult result ,@PathVariable String kitchenName, @PathVariable String itemName ) {
		if(result.hasErrors()) {
			return "edit_menu"; 
		}  
 		kitchenService.updateMenu(kitchenName, itemName ,item); 
		return "redirect:/homepage/kitchen/allItem/{kitchenName}"; 
	}
	@RequestMapping("/provider/kitchen/menu/delete/{kitchenName}/{itemName}")//------------------------DELETE-----MENU-----------------//
	public String deleteMenuItem(@PathVariable String itemName, @PathVariable String kitchenName) {
		itemRepo.deleteItemInMenu(itemName, kitchenName);
		return "redirect:/homepage/kitchen/allItem/{kitchenName}";
	}
	
	@RequestMapping("/provider/kitchenList")
	public String getKitchenList(Model model, Authentication authentication) {
		System.out.println(authentication.getName());
		List<Kitchen> kitchenList = kitchenService.ListAllKitchenByProviderEmail(authentication.getName());
		model.addAttribute("kitchenList", kitchenList);
		return "list_kitchen_for_provider";
	}



}
