package com.SpringBoot.CoffeeBean_BackEnd.Controller;

import com.SpringBoot.CoffeeBean_BackEnd.Model.Login;
import com.SpringBoot.CoffeeBean_BackEnd.Model.User;
import com.SpringBoot.CoffeeBean_BackEnd.Security.AuthenticationFilter;
import com.SpringBoot.CoffeeBean_BackEnd.Service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final AuthenticationFilter filter;

    @PostMapping("/register")
    public ResponseEntity<String> register (@Valid @RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login ( @Valid @RequestBody Login login) {
        return service.login(login);
    }

}

