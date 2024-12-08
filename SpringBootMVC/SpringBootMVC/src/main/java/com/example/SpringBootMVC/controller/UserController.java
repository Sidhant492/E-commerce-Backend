package com.example.SpringBootMVC.controller;

import com.example.SpringBootMVC.model.User;
import com.example.SpringBootMVC.service.JwtService;
import com.example.SpringBootMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @PostMapping("register")
    public User register(@RequestBody User user) {
        return service.saveUser(user);
    }
    @PostMapping("login")
    public String login(@RequestBody User user){

        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
        );
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        else{
            return "login Failed";
        }
    }


}
