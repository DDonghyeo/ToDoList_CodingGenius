package com.codingGenius.coding_genius.controller;

import com.codingGenius.coding_genius.dto.LoginRequestDto;
import com.codingGenius.coding_genius.dto.RegisterRequestDto;
import com.codingGenius.coding_genius.service.LoginService;
import com.codingGenius.coding_genius.utils.JwtTokenProvider;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/email")
    @ApiOperation(value = "이메일 검사 요청", notes = "Request : Request Body에 email을 담아서 보내면 해당 이메일로 전송 메세지가 전송됨 \n Response : Https Status 200")
    public ResponseEntity<?> requestEmailValidation(@RequestBody String email) {
        try {
            loginService.requestEmailValidation(email);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/email")
    @ApiOperation(value = "이메일 유효성 확인", notes = "Request Param : code , email \n 성공 / 실패는 Response Status로 구분함. 성공했을 경우 :200, 실패했을 경우 : 204")
    public ResponseEntity<?> checkEmailValidation(@RequestParam("code") String code, @RequestParam("email") String email) {
        try {
            if (loginService.checkEmailValidation(email, code)){
                return new ResponseEntity<>(HttpStatus.OK); //유효성 검사 통과
            } else return new ResponseEntity<>(HttpStatus.ACCEPTED); //유효성 검사 실패
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping("")
    @ApiOperation(value = "유저 일반 로그인 요청", notes = "Request : \n \t Request Body : loginRequestDto  - email,password \n passwordResponse : \n\t (String) Token")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequestDto loginRequestDto){
        if(loginService.userLogin(loginRequestDto.getEmail(), loginRequestDto.getPassword())){
            return ResponseEntity.ok(JwtTokenProvider.createAccessToken(loginRequestDto.getEmail()));
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register")
    @ApiOperation(value = "유저 회원가입", notes = "Request Body : Register Request Dto")
    public ResponseEntity<?> userRegister(@RequestBody RegisterRequestDto request){
        try {
            loginService.userRegister(request.getName(), request.getEmail(), request.getPassword());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
