package com.codingGenius.coding_genius.controller;

import com.codingGenius.coding_genius.domain.ToDo;
import com.codingGenius.coding_genius.domain.ToDoList;
import com.codingGenius.coding_genius.domain.Work;
import com.codingGenius.coding_genius.dto.WorkRequestDto;
import com.codingGenius.coding_genius.service.ToDoService;
import com.codingGenius.coding_genius.service.ToDoServiceImpl;
import com.codingGenius.coding_genius.service.WorkService;
import com.codingGenius.coding_genius.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping("/work")
public class WorkController {

    @Autowired
    WorkService workService;

    public void createWork(HttpServletRequest httpServletRequest, @RequestBody WorkRequestDto workRequestDto){
        //String email = JwtUtil.getBody(httpServletRequest.getHeader("Authorization"));
        //ToDo toDo = toDoService.findOne(email, workRequestDto.getTodoName());
    }

    @PostMapping("/")
    public ResponseEntity<ArrayList<Work>> getWork(HttpServletRequest httpServletRequest, @RequestBody String todoName){
        try{
            String email = JwtUtil.getBody(httpServletRequest.getHeader("Authorization"));
            return ResponseEntity.ok().body(workService.findWork(email, todoName));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
