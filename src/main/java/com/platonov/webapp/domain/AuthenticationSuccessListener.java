package com.platonov.webapp.domain;

import com.platonov.webapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
    @Autowired
    private UserRepo userRepo;

    public void onApplicationEvent(AuthenticationSuccessEvent ev) {

        String username = ev.getAuthentication().getName();

        User user = userRepo.findByUsername(username);
        if (user != null) {
            user.setLastDate(LocalDateTime.now());
            userRepo.save(user);
        }
    }
}
