package com.codingGenius.coding_genius.repository;

import com.codingGenius.coding_genius.domain.Work;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkRepository extends MongoRepository<Work, String> {
}
