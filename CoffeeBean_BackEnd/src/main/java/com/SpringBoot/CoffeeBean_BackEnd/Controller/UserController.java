package com.SpringBoot.CoffeeBean_BackEnd.Controller;

import com.SpringBoot.CoffeeBean_BackEnd.Model.User;
import com.SpringBoot.CoffeeBean_BackEnd.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("getAll")
    public List<User> get(){
        return service.getAll();
    }

    @PostMapping("post")
    public String post(@Valid @RequestBody User user){
        return service.PostService(user);
    }

}

