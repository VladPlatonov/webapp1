package com.platonov.webapp.controller;

import com.platonov.webapp.domain.Status;
import com.platonov.webapp.domain.User;
import com.platonov.webapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HomeController {


    @Autowired
    private UserRepo userRepo;


    @GetMapping
    public String index(@AuthenticationPrincipal User user, Model model){
        if(user !=null){
            model.addAttribute("user",user.getUsername());
        }
        model.addAttribute("user","anonymous");
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/foruser")
    public  String forUser(Model model) {
        model.addAttribute("users",userRepo.findAll());
        return "foruser";
    }
    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userRepo.deleteById(id);
        return "redirect:/foruser";
    }
    @GetMapping("user-block/{id}")
    public String banUser(@PathVariable("id") Long id){
        User user = userRepo.getById(id);
        user.getStatus().clear();
        user.getStatus().add(Status.BLOCK);
        userRepo.save(user);
        return "redirect:/foruser";
    }
    @GetMapping("user-unblock/{id}")
    public String unBanUser(@PathVariable("id") Long id){
        User user = userRepo.getById(id);
        user.getStatus().clear();
        user.getStatus().add(Status.UNBLOCK);
        userRepo.save(user);
        return "redirect:/foruser";
    }

}
