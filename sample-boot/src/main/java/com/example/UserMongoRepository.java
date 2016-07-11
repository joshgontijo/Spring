package com.example;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Josue on 11/07/2016.
 */
public interface UserMongoRepository extends MongoRepository<User, String> {
}
