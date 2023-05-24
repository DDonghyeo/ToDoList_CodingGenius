package com.codingGenius.coding_genius.service;

import ch.qos.logback.core.status.StatusBase;
import com.codingGenius.coding_genius.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseStatus;

public class LoginServiceImpl implements LoginService{

    @Autowired
    UserRepository userRepository;

    EmailService emailService;

    @Override
    public String requestEmailValidation(String email){
        try {
            emailService.sendMessage(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public

}
