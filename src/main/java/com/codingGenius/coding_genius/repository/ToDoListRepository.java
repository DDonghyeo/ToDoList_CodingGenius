package com.codingGenius.coding_genius.repository;

import com.codingGenius.coding_genius.domain.ToDoList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ToDoListRepository extends MongoRepository<ToDoList, String> {

    public Optional<ToDoList> findByEmail(String email);

    //public void update(String email);
}
