package com.codingGenius.coding_genius.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "emailValidation")
public class EmailValidation {
    @Id
    private String email;
    private Date exp;
    private String ePw;
}
