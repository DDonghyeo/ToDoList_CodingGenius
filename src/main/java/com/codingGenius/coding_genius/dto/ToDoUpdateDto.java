package com.codingGenius.coding_genius.dto;

import lombok.Getter;

import java.sql.Time;

@Getter
public class ToDoUpdateDto {

    private String oldName;

    private String newName;

    private Time expiration;

}
