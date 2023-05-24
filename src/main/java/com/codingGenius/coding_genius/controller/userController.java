package com.codingGenius.coding_genius.controller;

import com.codingGenius.coding_genius.dto.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user")
public class userController {

    public ResponseEntity<Optional<UserResponseDto>> getUserInfo(HttpServletRequest httpServletRequest){

    }

}
