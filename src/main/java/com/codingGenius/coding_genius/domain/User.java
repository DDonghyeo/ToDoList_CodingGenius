package com.codingGenius.coding_genius.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;

@Getter
@Setter
@Document(collection = "user")
public class User {

    private Long id;
    private String name;
    private String email;
}
