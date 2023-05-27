package com.codingGenius.coding_genius.repository;

import com.codingGenius.coding_genius.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long> {
    public Optional<User> findUserByEmail(String email);
}
