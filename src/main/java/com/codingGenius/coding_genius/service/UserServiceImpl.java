package com.codingGenius.coding_genius.service;

import com.codingGenius.coding_genius.dto.UserResponseDto;
import com.codingGenius.coding_genius.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements userService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponseDto getUserInfo() {
        try{
            log.info("Get User Info...");
            userRepository.findUserById()
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
