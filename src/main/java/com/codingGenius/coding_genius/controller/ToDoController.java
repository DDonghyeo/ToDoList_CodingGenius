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
        try{
            return toDoService.save(toDoRequestDto);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/ToDo")
    public List<ToDoResponseDto> getToDo(@RequestBody Long userId){
        try{
            return toDoService.findAll(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/ToDo/{id}")
    public ToDoResponseDto updateToDo(@PathVariable("id") long id, @RequestBody ToDoRequestDto toDoRequestDto){
        try{
            return toDoService.update(id, toDoRequestDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/ToDo/{id}")
    public void deleteToDo(@PathVariable("id") long id){
        try{
            toDoService.delete(id);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
