package com.SpringBoot.CoffeeBean_BackEnd.Repository;

import com.SpringBoot.CoffeeBean_BackEnd.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,Long> {
}

