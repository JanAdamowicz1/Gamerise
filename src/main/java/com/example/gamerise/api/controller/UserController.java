package com.example.gamerise.api.controller;

import com.example.gamerise.api.model.User;
import com.example.gamerise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public User getUserById(@RequestParam int userId){
        Optional user = userService.getUserById(userId);
        if (user.isPresent()) {
            return (User) user.get();
        } else {
            return null;
        }
    }

    @GetMapping("/me")
    public User getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.findByUsername(userDetails.getUsername());
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        Optional userList = userService.getAllUsers();
        if (userList.isPresent()) {
            return (List<User>) userList.get();
        } else {
            return null;
        }
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
