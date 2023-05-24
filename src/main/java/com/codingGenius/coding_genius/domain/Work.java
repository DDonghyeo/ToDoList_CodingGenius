package com.codingGenius.coding_genius.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "work")
public class Work {
    private Long id;
    private String name;
    private boolean complete;
}
