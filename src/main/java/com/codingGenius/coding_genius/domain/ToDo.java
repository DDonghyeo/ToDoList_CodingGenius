package com.codingGenius.coding_genius.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "ToDo")
public class ToDo {
    private Long id;
    private String name;
    private String expiration;
    private Long userId;
}