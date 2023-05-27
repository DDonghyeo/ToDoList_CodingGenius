package com.codingGenius.coding_genius.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Getter
public class ToDoRequestDto {

    private String name;

    private Time expiration;

    private boolean complete;

    public ToDoRequestDto(ToDoUpdateDto toDoUpdateDto){
        this.name = toDoUpdateDto.getNewName();
        this.expiration = toDoUpdateDto.getExpiration();
        this.complete = toDoUpdateDto.isComplete();
    }

}
