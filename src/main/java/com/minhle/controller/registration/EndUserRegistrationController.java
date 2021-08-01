package com.minhle.controller.registration; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import com.minhle.config.security.UserRegistrationDto;
import com.minhle.model.user.EndUser; 
import com.minhle.service.EndUserService;  
import javax.validation.Valid;

@Controller
@RequestMapping("/registration/enduser")
public class EndUserRegistrationController { 
    @Autowired
    private EndUserService endUserService; 
    @ModelAttribute("endUser")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    } 
    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration_enduser";
    }
    @PostMapping
    public String registerEndUserAccount(@ModelAttribute("endUser") 
    								  @Valid UserRegistrationDto userRegistrationDto, 
                                      BindingResult result)
    { 
        EndUser existing = endUserService.findByEmail(userRegistrationDto.getEmail());
        System.out.println("no user have this name found in database  "+ existing );
        if (existing.getEmail() != "Empty"){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "registration_enduser";
        }
         

        endUserService.saveFromDTO(userRegistrationDto);
        return "redirect:/registration/enduser?success";
    }

}