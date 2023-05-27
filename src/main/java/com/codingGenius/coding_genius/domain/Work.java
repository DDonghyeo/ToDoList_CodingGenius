package com.codingGenius.coding_genius.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
public class Work {

    private String name;

    @Setter
    private boolean complete;

    private String memo;
}
