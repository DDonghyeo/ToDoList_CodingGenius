package com.codingGenius.coding_genius.service;

import com.codingGenius.coding_genius.domain.ToDo;
import com.codingGenius.coding_genius.domain.ToDoList;
import com.codingGenius.coding_genius.dto.ToDoRequestDto;

public interface ToDoService {

    public void save(String email, ToDoRequestDto toDoRequestDto);

    public ToDoList findByEmail(String email);

    public void update(String email, ToDoRequestDto toDoRequestDto);

    public void delete(String email, String name);

    public ToDo findOne(String email, String todoName);
}
