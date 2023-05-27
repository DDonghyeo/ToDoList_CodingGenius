package com.codingGenius.coding_genius.controller;

import com.codingGenius.coding_genius.domain.Work;
import com.codingGenius.coding_genius.dto.WorkDeleteDto;
import com.codingGenius.coding_genius.dto.WorkRequestDto;
import com.codingGenius.coding_genius.dto.WorkUpdateDto;
import com.codingGenius.coding_genius.service.WorkService;
import com.codingGenius.coding_genius.utils.JwtUtil;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/work")
public class WorkController {

    @Autowired
    WorkService workService;

    @PostMapping("/")
    @ApiOperation(value = "작업 생성", notes = "Request : Request Header에 Authorization : token, Request Body에 todoName, workName, complete, memo를 담아서 보내면 작업이 생성됨\nResponse : Https status 200")
    public ResponseEntity<?> createWork(HttpServletRequest httpServletRequest, @RequestBody WorkRequestDto workRequestDto){
        try{
            String email = JwtUtil.getBody(httpServletRequest.getHeader("Authorization"));
            workService.save(email, workRequestDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/{todoName}")
    @ApiOperation(value = "작업 가져오기", notes = "Request : Request Header에 Authorization : token 담아서 요청\nResponse : ArrayList<Work>")
    public ResponseEntity<ArrayList<Work>> getWork(HttpServletRequest httpServletRequest, @PathVariable("todoName") String todoName){
        try{
            String email = JwtUtil.getBody(httpServletRequest.getHeader("Authorization"));
            return ResponseEntity.ok().body(workService.findWork(email, todoName));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/")
    @ApiOperation(value = "작업 업데이트", notes = "Request : Request Header에 Authorization : token, Request Body에 todoName, oldName, newName, complete, memo 담아서 요청\n\toldName: 수정 전 작업 이름, newName: 수정 후 작업 이름\nResponse : Https Status 200")
    public ResponseEntity<?> updateWork(HttpServletRequest httpServletRequest, @RequestBody WorkUpdateDto workUpdateDto){
        try{
            String email = JwtUtil.getBody(httpServletRequest.getHeader("Authorization"));
            workService.update(email, workUpdateDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/delete")
    @ApiOperation(value = "작업 삭제", notes = "Request : Request Header에 Authorization : token, Request Body에 todoName, workName 담아서 요청\nResponse : Https Status 200")
    public ResponseEntity<?> deleteWork(HttpServletRequest httpServletRequest, @RequestBody WorkDeleteDto workDeleteDto){
        try{
            String email = JwtUtil.getBody(httpServletRequest.getHeader("Authorization"));
            workService.delete(email, workDeleteDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
