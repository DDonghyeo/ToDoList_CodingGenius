package com.codingGenius.coding_genius.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;

@Getter
@Setter
@Document(collection = "user")
public class User {

    private Long idx;
    private String name;
    private String email;

    private String id;
    private String password;
}
