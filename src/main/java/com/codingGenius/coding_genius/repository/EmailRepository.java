package com.codingGenius.coding_genius.repository;

import com.codingGenius.coding_genius.domain.EmailValidation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailRepository extends MongoRepository<EmailValidation, String> {
}
