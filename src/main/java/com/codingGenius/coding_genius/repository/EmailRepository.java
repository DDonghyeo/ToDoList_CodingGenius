package com.codingGenius.coding_genius.repository;

import com.codingGenius.coding_genius.domain.EmailValidation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmailRepository extends MongoRepository<EmailValidation, String> {
    public List<EmailValidation> findAllByEmail(String email);
}
