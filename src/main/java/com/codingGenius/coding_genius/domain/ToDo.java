package com.codingGenius.coding_genius.domain;

import com.codingGenius.coding_genius.dto.ToDoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import springfox.documentation.spring.web.json.Json;

import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;

@Getter @Setter
@AllArgsConstructor
public class ToDo {

    private String name;

    private Time expiration;

    private ArrayList<Work> workArrayList;

    private boolean complete;

}