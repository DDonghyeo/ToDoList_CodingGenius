package com.codingGenius.coding_genius.repository;

import com.codingGenius.coding_genius.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {
    public User findUserByEmail(String email);
}
