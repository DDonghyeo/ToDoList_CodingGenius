package com.codingGenius.coding_genius.service;

import com.codingGenius.coding_genius.domain.ToDo;
import com.codingGenius.coding_genius.domain.ToDoList;
import com.codingGenius.coding_genius.dto.ToDoResponseDto;

import java.util.List;

public interface ToDoService {

    public void save(String email, ToDo toDo);

    public ToDoList findByEmail(String email);

    public void update(String email);

    public void delete(Long id);

    public List<ToDoResponseDto> sort(Long userId);
}
