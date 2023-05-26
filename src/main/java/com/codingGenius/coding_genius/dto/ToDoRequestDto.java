package com.codingGenius.coding_genius.dto;

import lombok.Getter;

import java.sql.Time;

@Getter
public class ToDoRequestDto {

    private String name;

    private Time expiration;

    private boolean complete;

}
