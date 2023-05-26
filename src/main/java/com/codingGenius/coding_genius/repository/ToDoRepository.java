package com.codingGenius.coding_genius.repository;

import com.codingGenius.coding_genius.domain.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDo, String> {
}
