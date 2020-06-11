package com.sync.syncjavbackend.repositories;

import com.sync.syncjavbackend.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "userdata",
        path = "usertdata")
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);
}
