package com.codingGenius.coding_genius.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
public class Work {

    private String name;

    private boolean complete;
}
