package com.platonov.webapp.controller;

import com.platonov.webapp.domain.RegistrationForm;
import com.platonov.webapp.domain.Status;
import com.platonov.webapp.domain.User;
import com.platonov.webapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {


    @Autowired
    private UserRepo userRepo;


    @GetMapping
    public String index(){
        return "redirect:/foruser";
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
    @RequestMapping(value = "/delete")
    public String delete(@RequestParam("idChecked") Long[] ids){

        if(ids != null)
            for(Long id : ids)
                userRepo.deleteById(id);
        return "redirect:/foruser";
    }
    @RequestMapping(value = "/block")
    public String blockUser(@RequestParam("idChecked") Long[] ids){
        if(ids != null)
            for(Long id : ids) {
                User user = userRepo.getById(id);
                user.getStatus().clear();
                user.getStatus().add(Status.BLOCK);
                user.setStatusLogin("Block");
                user.setIsAccountNonLocked(false);
                userRepo.save(user);
            }
        return "redirect:/foruser";
    }
    @RequestMapping(value = "/unblock")
    public String unblockUser(@RequestParam("idChecked") Long[] ids){
        if(ids != null)
            for(Long id : ids) {
                User user = userRepo.getById(id);
                user.getStatus().clear();
                user.getStatus().add(Status.UNBLOCK);
                user.setStatusLogin("Unblock");
                user.setIsAccountNonLocked(true);
                userRepo.save(user);
            }
        return "redirect:/foruser";
    }
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

}
