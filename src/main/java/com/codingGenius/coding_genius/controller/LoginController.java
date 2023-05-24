package com.codingGenius.coding_genius.controller;

import com.codingGenius.coding_genius.service.LoginService;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/email")
    @ApiOperation(value = "이메일 유효성 검사", notes = "")
    public ResponseEntity emailValidation(@RequestBody String email){
        try {
            loginService.
        }
    }
}
