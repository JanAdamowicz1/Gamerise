package com.example.gamerise.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @GetMapping("/{userId}")
    public String getUserById(){
        return "User";
    }
    @GetMapping("/all")
    public String getAllUsers(){
        return "All Users";
    }
    @GetMapping("/add")
    public String addUser(){
        return "User Added";
    }
    @GetMapping("/update")
    public String updateUser() {
        return "User Updated";
    }
    @GetMapping("/delete")
    public String deleteUser() {
        return "User Deleted";
    }
}
