package com.gt9.travelstories.repository;

import com.gt9.travelstories.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{email : ?0}")
    List<User> findUserByEmail(String email);

}
