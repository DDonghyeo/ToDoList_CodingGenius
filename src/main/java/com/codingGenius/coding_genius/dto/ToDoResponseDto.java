package com.codingGenius.coding_genius.dto;

import com.codingGenius.coding_genius.domain.Work;
import lombok.Data;

import java.sql.Time;
import java.util.ArrayList;

@Data
public class ToDoResponseDto {

    private String name;

    private Time expiration;

    private ArrayList<Work> workArrayList;

    private boolean complete;
}
