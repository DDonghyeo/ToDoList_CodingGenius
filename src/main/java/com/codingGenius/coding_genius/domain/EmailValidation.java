package com.codingGenius.coding_genius.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "emailValidation")
public class EmailValidation {
    private String email;
    private Time exp;
    private String ePw;
}
