package com.minhle.controller.registration;
 
 
import org.springframework.beans.factory.annotation.Autowired;
  
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.minhle.config.security.KitchenUserRegistrationDto;
import com.minhle.model.user.KitchenProviderUser;
import com.minhle.service.KitchenProviderService;

//import com.minhle.service.NotificationEmailService;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/registration/provider")
public class ProviderRegistrationController { 
    @Autowired
    private KitchenProviderService kitchenProviderService; 
    @ModelAttribute("kitchenProviderUser")
    public KitchenUserRegistrationDto userRegistrationDto() {
        return new KitchenUserRegistrationDto();
    } 
    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration_provider";
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
            return "registration_provider";
        }
         

        kitchenProviderService.saveFromDTO(kitchenUserRegistrationDto);
        return "redirect:/registration/provider?success";
    }

}