package com.codingGenius.coding_genius.service;

import com.codingGenius.coding_genius.domain.User;
import com.codingGenius.coding_genius.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    @Autowired
    UserRepository userRepository;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    //이메일 유효성 요청
    @Override
    public void requestEmailValidation(String email){
        try {
            emailService.sendMessage(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //이메일 유효성 검사
    public boolean checkEmailValidation(String email, String code){
            return emailService.ValidationCheck(email, code);
    }

    //유저 회원가입
    public void userRegister(String name, String email, String password) {
        log.info("user name : " + name);
        log.info("user email : " + email);
        log.info("user pw : " + password);
        String password_encoded = passwordEncoder.encode(password);
        User new_user = new User(email, name, password_encoded);
        userRepository.save(new_user);
    }

    //유저 로그인
    public boolean userLogin(String email, String password) {
        try {
            Optional<User> user = userRepository.findUserByEmail(email);
            return user.filter(value -> passwordEncoder.matches(password, value.getPassword())).isPresent();
        } catch (Exception e) {
            return false;
        }
    }

}
