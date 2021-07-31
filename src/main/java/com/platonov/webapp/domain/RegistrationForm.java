package com.platonov.webapp.domain;


import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Collections;


@Data
public class RegistrationForm {
    @NotBlank (message = "Name should not be empty")
    private String username;
    @NotEmpty(message = "Password should not be empty")
    private String password;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    public  User toUser(PasswordEncoder passwordEncoder){
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        user.setStatus(Collections.singleton(Status.UNBLOCK));
        user.setStatusLogin("Unblock");
        user.setIsAccountNonLocked(true);
        return user;
    }

}
