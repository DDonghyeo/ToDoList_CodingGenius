package com.codingGenius.coding_genius.domain;

import com.codingGenius.coding_genius.dto.ToDoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {

    private String name;

    private String expiration;

    private ArrayList<Work> workArrayList;

    private boolean complete;

    public ToDo(ToDoRequestDto toDoRequestDto){
        this.name = toDoRequestDto.getName();
        this.expiration = toDoRequestDto.getExpiration();
    }

    public boolean isWorkNull() {
        return workArrayList == null;
    }

}