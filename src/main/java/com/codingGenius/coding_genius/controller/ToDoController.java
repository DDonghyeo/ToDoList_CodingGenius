package com.codingGenius.coding_genius.controller;

import com.codingGenius.coding_genius.domain.ToDo;
import com.codingGenius.coding_genius.domain.ToDoList;
import com.codingGenius.coding_genius.dto.ToDoRequestDto;
import com.codingGenius.coding_genius.dto.ToDoUpdateDto;
import com.codingGenius.coding_genius.service.ToDoService;
import com.codingGenius.coding_genius.utils.JwtUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/todo")
public class ToDoController {

    @Autowired
    ToDoService toDoService;

    @PostMapping("")
    @ApiOperation(value = "할 일 생성", notes = "Request : Request Header에 Authorization : token, Request Body에 name, expiration, complete를 담아서 보내면 할 일이 생성됨\nResponse : Https Status 200")
    public ResponseEntity<?> createToDo(HttpServletRequest httpServletRequest, @RequestBody ToDoRequestDto toDoRequestDto){
        try{
            String email = JwtUtil.getBody(httpServletRequest.getHeader("Authorization"));
            toDoService.save(email, toDoRequestDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("")
    @ApiOperation(value = "할 일 가져오기", notes = "Request : Request Header에 Authorization : token 넣어서 요청\nResponse : ArrayList<ToDo>")
    public ResponseEntity<ArrayList<ToDo>> getToDoList(HttpServletRequest httpServletRequest){
        try{
            String email = JwtUtil.getBody(httpServletRequest.getHeader("Authorization"));//email?
            ToDoList toDoList = toDoService.findByEmail(email);
            return ResponseEntity.ok().body(toDoList.getToDoArrayList());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("")
    @ApiOperation(value = "할 일 업데이트", notes = "Request : Request Header에 Authorization : token, Request Body에 oldName, newName, expiration, complete 넣어서 요청\nResponse : Https Status 200")
    public ResponseEntity<?> updateToDo(HttpServletRequest httpServletRequest, @RequestBody ToDoUpdateDto toDoUpdateDto){
        try{
            String email = JwtUtil.getBody(httpServletRequest.getHeader("Authorization"));
            toDoService.update(email, toDoUpdateDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("")
    @ApiOperation(value = "할 일 삭제", notes = "Request : Request Header에 Authorization : token, Request Body에 todoName 넣어서 요청\nResponse : Https Status 200")
    public ResponseEntity<?> deleteToDo(HttpServletRequest httpServletRequest, @RequestParam String name){
        try{
            String email = JwtUtil.getBody(httpServletRequest.getHeader("Authorization"));
            toDoService.delete(email, name);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/complete")
    @ApiOperation(value = "할 일 완료 표시 변경", notes = "Request : Request Header에 Authorization : token, Request Body에 todoName 넣어서 요청\nResponse : Https Status 200")
    public ResponseEntity<?> completeToDo(HttpServletRequest httpServletRequest, @RequestParam String name){
        try{
            String email = JwtUtil.getBody(httpServletRequest.getHeader("Authorization"));
            toDoService.complete(email, name);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
