package com.codingGenius.coding_genius.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Time;

@Getter
@Setter
@Document(collection = "user")
public class EmailValidation {
    private Long id;
    private Time exp;
    private String ePw;
}
