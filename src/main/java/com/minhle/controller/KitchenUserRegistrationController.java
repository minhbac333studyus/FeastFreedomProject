package com.minhle.controller; 
import javax.validation.Valid; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import com.minhle.model.user.KitchenProviderUser;
import com.minhle.service.KitchenProviderService; 
 
@Controller
@RequestMapping("/registration")
public class KitchenUserRegistrationController {

    @Autowired
    private KitchenProviderService providerService;

    @ModelAttribute("provider")
    public KitchenUserRegistrationDto userRegistrationDto() {
        return new KitchenUserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("provider") @Valid KitchenUserRegistrationDto kitProviderDto,
                                      BindingResult result){

        KitchenProviderUser existing = providerService.findByEmail(kitProviderDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "registration";
        }

        providerService.save(kitProviderDto);
        return "redirect:/registration?success";
    }

}
