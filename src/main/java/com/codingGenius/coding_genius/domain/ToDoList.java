package com.codingGenius.coding_genius.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
@Document(collection = "ToDoList")
public class ToDoList {

    private String email;

    private ArrayList<ToDo> toDoArrayList;
}
