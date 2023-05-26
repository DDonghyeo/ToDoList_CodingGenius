package com.codingGenius.coding_genius.controller;

import com.codingGenius.coding_genius.dto.ToDoRequestDto;
import com.codingGenius.coding_genius.dto.ToDoResponseDto;
import com.codingGenius.coding_genius.service.ToDoService;
import com.codingGenius.coding_genius.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoController {

    @Autowired
    ToDoService toDoService;

    @PostMapping("/")
    public ResponseEntity<?> createToDo(ToDoRequestDto toDoRequestDto){
        try{
            toDoService.save(toDoRequestDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/")
    public List<ToDoResponseDto> getToDo(HttpServletRequest httpServletRequest){
        try{
            String pk = JwtUtil.getBody(httpServletRequest.getHeader("Authorization"));
            return toDoService.findAll(pk);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateToDo(@PathVariable("id") long id, @RequestBody ToDoRequestDto toDoRequestDto){
        try{
            toDoService.update(id, toDoRequestDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteToDo(@PathVariable("id") long id){
        try{
            toDoService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
