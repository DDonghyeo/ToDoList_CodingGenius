package com.codingGenius.coding_genius.controller;

import com.codingGenius.coding_genius.dto.UserResponseDto;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user")
public class userController {

    //사용자 정보 얻기
    @GetMapping("/")
    @ApiOperation(value = "#1 사용자 정보 얻기", notes = "memo")
    public ResponseEntity<Optional<UserResponseDto>> getUserInfo(HttpServletRequest httpServletRequest){

    }

}
