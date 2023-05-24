package com.codingGenius.coding_genius.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Time;

@Getter
@Setter
@Document(collection = "ToDo")
public class ToDo {
    private Long id;
    private String name;
    private Time expiration;
    private Long userId;
}