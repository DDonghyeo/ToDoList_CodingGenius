package com.codingGenius.coding_genius.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import springfox.documentation.spring.web.json.Json;

import java.sql.Array;
import java.sql.Time;

@Getter
@Setter
@Document(collection = "ToDo")
public class ToDo {
    private String name;
    private Time expiration;
    private Array toDoList;
}