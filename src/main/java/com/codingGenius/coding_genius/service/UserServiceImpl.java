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
    public UserResponseDto getUserInfo(String email) {
        try{
            log.info("user email :"+email);
            User user = userRepository.findUserByEmail(email);
            return new UserResponseDto(user.getName(),user.getEmail());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getUserName(String email){
        try{
            User user = userRepository.findUserByEmail(email);
            return user.getName();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
