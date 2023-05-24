package com.codingGenius.coding_genius.service;

import com.codingGenius.coding_genius.domain.ToDo;
import org.springframework.http.ResponseEntity;

public interface ToDoService {
    public ToDoResponseDto save(ToDoRequestDto toDoRequestDto);

    public List<ToDoResponseDto> findAll(Long userId);

    public ToDoResponseDto update(Long id, ToDoRequestDto toDoRequestDto);

    public void delete(Long id);

    public List<ToDoResponseDto> sort(Long userId);
}
