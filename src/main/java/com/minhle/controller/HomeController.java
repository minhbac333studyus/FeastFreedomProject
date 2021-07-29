package com.minhle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String root()
	{
		return "index"; //acess index.html
	}
	@GetMapping("/login")
    public String login(Model model) {
        return "login"; //access login.html
    }

    @GetMapping("/kitchenProviderUser")
    public String kitchenProviderIndex() {
        return "kitchenProviderUser/index";
    }
}
