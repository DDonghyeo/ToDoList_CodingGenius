package com.codingGenius.coding_genius.controller;

import com.codingGenius.coding_genius.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ToDoController {

    @Autowired
    ToDoService toDoService;

    @PostMapping("/ToDo")
    public ToDoResponseDto createToDo(ToDoRequestDto toDoRequestDto){
        return toDoService.save(toDoRequestDto);
    }

    public List<ToDoResponseDto> getToDo(){

    }
}
