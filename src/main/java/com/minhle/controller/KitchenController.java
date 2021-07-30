package com.minhle.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.minhle.model.kitchen.Kitchen;
import com.minhle.service.KitchenService;


@RestController
public class KitchenController {
	@Autowired
	KitchenService kitchenService;
	
 
	
	@GetMapping("/kitchen")
	public List<Kitchen> getKitchens() {
		System.out.println(kitchenService.ListAllKitchen());
		return kitchenService.ListAllKitchen();
	}
	/*@RequestMapping("/kitchen")
	public String getKitchens(Model model) {
		List<Kitchen> kitchens = kitchenService.ListAllKitchen();
		model.addAttribute("kitchens", kitchens);
		return "kitchen";
	}*/
	/*
	 @RequestMapping("/list_countries")
	public String listCountries(Model model) {
		List<Country> list = countService.getCountryList();
		model.addAttribute("countries", list);
		return "country"; // the view name
	}
	 */
	/*
	 public String viewAllPostsAndComments(Model model){
    List<Post> postList = postRepository.findAll();
    model.addAttribute("postList", postList);
    
    return "all-posts";
}
	 */
	
	@GetMapping("/kitchen/{id}")
	public ResponseEntity<Kitchen> getKitchen(@PathVariable String id) {
		try {
			Kitchen kitchen = kitchenService.getDetailOfOneKitchen(id);
			return new ResponseEntity<Kitchen>(kitchen, HttpStatus.OK);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<Kitchen>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/kitchen/{id}")
	public ResponseEntity<?> updateKitchen(@RequestBody Kitchen kitchen, @PathVariable String id) {
		try {
			Kitchen updateKitchen = kitchenService.getDetailOfOneKitchen(id);
			System.out.println(updateKitchen);
			kitchen.setKitchenid(id);
			kitchenService.saveKitchen(updateKitchen);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/new")
	public void addKitchen(@RequestBody Kitchen kitchen) {
		kitchenService.saveKitchen(kitchen);
	}
	@RequestMapping("/kitchen/new_kitchen")
	public ModelAndView showCreateNewKitchenPage() {
		ModelAndView mav = new ModelAndView("new_service_provider");
		Kitchen kitchen = new Kitchen();
		mav.addObject("kitchen", kitchen);
		return mav;
	}
	/*
	@DeleteMapping("/kitchen/{id}")
	public void delete(@PathVariable String id) {
		kitchenService.deleteKitchen(id);
	}*/
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveKitchen(@ModelAttribute("kitchen") Kitchen kitchen) {
		kitchenService.saveKitchen(kitchen);
		return "redirect:/";
	}
	
}
