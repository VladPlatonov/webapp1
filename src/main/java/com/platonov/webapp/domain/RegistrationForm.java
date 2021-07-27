package com.platonov.webapp.domain;


import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;


@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String email;

    public  User toUser(PasswordEncoder passwordEncoder){
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        user.setStatus(Collections.singleton(Status.UNBLOCK));
        return user;
    }

}
