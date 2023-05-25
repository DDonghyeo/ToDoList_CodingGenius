package com.codingGenius.coding_genius.service;

import com.codingGenius.coding_genius.domain.User;
import com.codingGenius.coding_genius.dto.UserResponseDto;
import com.codingGenius.coding_genius.repository.UserRepository;
import com.codingGenius.coding_genius.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserResponseDto getUserInfo(String token) {
        try{
            log.info("Get User Info...");
            String user_email = JwtUtil.getBody(token);
            User user = userRepository.findUserByEmail(user_email);
            return new UserResponseDto(user.getName(),user.getEmail());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getUserName(String token){
        try{
            String user_email = JwtUtil.getBody(token);
            User user = userRepository.findUserByEmail(user_email);
            return user.getName();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
