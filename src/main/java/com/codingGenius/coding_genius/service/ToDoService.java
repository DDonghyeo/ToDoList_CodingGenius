package com.codingGenius.coding_genius.service;

import com.codingGenius.coding_genius.domain.ToDo;
import org.springframework.http.ResponseEntity;

public interface ToDoService {
    public ToDo save(ToDoRequestDto toDoRequestDto);

    public List<ToDoResponseDto> findAll();

    public ToDo update(Long id, ToDoRequestDto toDoRequestDto);

    public void delete(Long id);
}
