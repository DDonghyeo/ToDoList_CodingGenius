package com.codingGenius.coding_genius.controller;

import com.codingGenius.coding_genius.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class ToDoController {

    @Autowired
    ToDoService toDoService;

    @PostMapping("/ToDo")
    public ToDoResponseDto createToDo(ToDoRequestDto toDoRequestDto){
        return toDoService.save(toDoRequestDto);
    }

    @GetMapping("/ToDo")
    public List<ToDoResponseDto> getToDo(@RequestBody Long userId){
        return toDoService.findAll(userId);
    }

    @PutMapping("/ToDo/{id}")
    public ToDoResponseDto updateToDo(@PathVariable("id") long id, @RequestBody ToDoRequestDto toDoRequestDto){
        
    }
}
