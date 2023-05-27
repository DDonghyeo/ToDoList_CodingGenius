package com.codingGenius.coding_genius.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter @Setter
@AllArgsConstructor
@Document(collection = "ToDoList")
public class ToDoList {

    @Id
    private String email;

    private ArrayList<ToDo> toDoArrayList;
}
