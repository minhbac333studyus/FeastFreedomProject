package com.minhle.controller;
 
 
import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.minhle.model.user.KitchenProviderUser;
import com.minhle.service.KitchenProviderService;
//import com.minhle.service.NotificationEmailService;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class ProviderRegistrationController {

    @Autowired
    private KitchenProviderService kitchenProviderService;
    
//    @Autowired
//    private NotificationEmailService notificationEmailService;
    @ModelAttribute("kitchenProviderUser")
    public KitchenUserRegistrationDto userRegistrationDto() {
        return new KitchenUserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }
    
 
    
    @PostMapping
    public String registerUserAccount(@ModelAttribute("kitchenProviderUser") 
    								  @Valid KitchenUserRegistrationDto kitchenUserRegistrationDto, 
                                      BindingResult result)
    { 
        KitchenProviderUser existing = kitchenProviderService.findByEmail(kitchenUserRegistrationDto.getEmail());
        System.out.println(existing );
        if (existing.getEmail() != "Empty"){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "registration";
        }
         

        kitchenProviderService.saveFromDTO(kitchenUserRegistrationDto);
        return "redirect:/registration?success";
    }

}