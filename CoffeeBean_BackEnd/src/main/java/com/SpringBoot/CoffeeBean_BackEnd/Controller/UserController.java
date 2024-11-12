package com.SpringBoot.CoffeeBean_BackEnd.Controller;

import com.SpringBoot.CoffeeBean_BackEnd.Model.User;
import com.SpringBoot.CoffeeBean_BackEnd.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

