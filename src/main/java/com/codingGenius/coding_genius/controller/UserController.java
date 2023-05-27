package com.codingGenius.coding_genius.controller;

import com.codingGenius.coding_genius.dto.NameResponseDto;
import com.codingGenius.coding_genius.dto.UserResponseDto;
import com.codingGenius.coding_genius.service.UserService;
import com.codingGenius.coding_genius.utils.JwtUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    @ApiOperation(value = "사용자 정보 얻기, 회원정보 관리 화면에서 사용", notes = "Request : Request Header에 Authorization : token 넣어서 요청 \n Response : Response Body : User Info")
    public ResponseEntity<UserResponseDto> getUserInfo(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Authorization");
        String email  = JwtUtil.getBody(token);
        return  ResponseEntity.ok(userService.getUserInfo(email));
    }

    @GetMapping("/name")
    @ApiOperation(value = "사용자 이름 얻기, 홈 화면에서 사용", notes = " Request : Request Header에 Authorization : token 넣어서 요청 \n Response : Response Body : String")
    public ResponseEntity<NameResponseDto> getUsername(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        String email  = JwtUtil.getBody(token);
        NameResponseDto nameResponseDto = new NameResponseDto(userService.getUserName(email));
        return ResponseEntity.ok(nameResponseDto);
    }
}
