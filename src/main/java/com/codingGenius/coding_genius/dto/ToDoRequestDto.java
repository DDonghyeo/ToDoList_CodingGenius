package com.codingGenius.coding_genius.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Getter
@AllArgsConstructor
public class ToDoRequestDto {

    private String name;

    private Time expiration;


}
