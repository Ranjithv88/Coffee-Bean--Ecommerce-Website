package com.SpringBoot.CoffeeBean_BackEnd.Service;

import com.SpringBoot.CoffeeBean_BackEnd.Model.User;
import com.SpringBoot.CoffeeBean_BackEnd.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> getAll(){
        return repository.findAll();
    }
}

