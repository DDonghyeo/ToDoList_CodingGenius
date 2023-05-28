package com.codingGenius.coding_genius.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ToDoRequestDto {

    private String name;

    private String expiration;


}
