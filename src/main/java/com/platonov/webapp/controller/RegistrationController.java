package com.platonov.webapp.controller;


import com.platonov.webapp.domain.RegistrationForm;
import com.platonov.webapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;
    @ModelAttribute("form")
    public RegistrationForm registrationForm() {
        return new RegistrationForm();
    }
    @GetMapping
    public String registration(){
        return "registration";
    }
    @PostMapping
    public  String processUser(@ModelAttribute("form") @Valid RegistrationForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "registration";
        else{
        userRepo.save(form.toUser(passwordEncoder));

        return "redirect:/login";
        }
    }

}
