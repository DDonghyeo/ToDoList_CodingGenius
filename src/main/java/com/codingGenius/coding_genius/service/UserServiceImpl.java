package com.codingGenius.coding_genius.service;

import com.codingGenius.coding_genius.domain.User;
import com.codingGenius.coding_genius.dto.UserResponseDto;
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
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

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

    @Override
    public void changePassword(String email, String password){
        try{
            Optional<User> user = userRepository.findUserByEmail(email);
            if(user.isPresent()){
                String password_encoded = passwordEncoder.encode(password);
                userRepository.save(new User(email, user.get().getName(), password_encoded));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
