package com.codingGenius.coding_genius.service;

import com.codingGenius.coding_genius.domain.User;
import com.codingGenius.coding_genius.dto.UserResponseDto;
import com.codingGenius.coding_genius.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserResponseDto getUserInfo(String email) {
        try{
            log.info("user email :"+email);
            Optional<User> user = userRepository.findUserByEmail(email);
            if (user.isPresent()) {
                return new UserResponseDto(user.get().getName(),user.get().getEmail());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getUserName(String email){
        try{
            Optional<User> user = userRepository.findUserByEmail(email);
            return user.map(User::getName).orElse(null);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(String email) {
        try {
            Optional<User> user = userRepository.findUserByEmail(email);
            user.ifPresent(value -> userRepository.delete(value));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
