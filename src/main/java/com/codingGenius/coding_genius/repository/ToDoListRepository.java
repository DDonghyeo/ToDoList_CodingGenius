package com.codingGenius.coding_genius.repository;

import com.codingGenius.coding_genius.domain.ToDoList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoListRepository extends MongoRepository<ToDoList, String> {
}
