package com.codingGenius.coding_genius.service;

import ch.qos.logback.core.status.StatusBase;
import com.codingGenius.coding_genius.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseStatus;

public class LoginServiceImpl implements LoginService{

    @Autowired
    UserRepository userRepository;

    EmailUtil emailUtil;

    @Override
    public boolean requestEmailValidation(String email){
        try {
            emailUtil.sendMessage(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkEmailValidation(String email, String code){
            return emailUtil.ValidationCheck(email, code);
    }

}
