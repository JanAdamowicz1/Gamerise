package com.example.gamerise.api.controller;

import com.example.gamerise.api.dto.UserDto;
import com.example.gamerise.api.model.Game;
import com.example.gamerise.api.model.User;
import com.example.gamerise.api.model.UserObservedAccount;
import com.example.gamerise.service.UserService;
import com.example.gamerise.service.UserObservedAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-observed-account")
public class UserObservedAccountController {
    private final UserService userService;
    private final UserObservedAccountService userObservedAccountService;

    @Autowired
    public UserObservedAccountController(UserService userService, UserObservedAccountService userObservedAccountService) {
        this.userService = userService;
        this.userObservedAccountService = userObservedAccountService;
    }

    @PostMapping("/follow/{observingUserId}/{observedUserId}")
    public UserObservedAccount followUser(@PathVariable int observingUserId, @PathVariable int observedUserId) {
        User observingUser = userService.getUserById(observingUserId).orElseThrow(() -> new RuntimeException("User not found"));
        User observedUser = userService.getUserById(observedUserId).orElseThrow(() -> new RuntimeException("User not found"));
        return userObservedAccountService.followUser(observingUser, observedUser);
    }

    @GetMapping("/observing/{userId}")
    public List<UserDto> getObservingUsers(@PathVariable int userId) {
        return userObservedAccountService.getObservingUsers(userId);
    }

    @GetMapping("/observed/{userId}")
    public List<UserDto> getObservedUsers(@PathVariable int userId) {
        return userObservedAccountService.getObservedUsers(userId);
    }

    @DeleteMapping("/unfollow/{observingUserId}/{observedUserId}")
    public void unfollowUser(@PathVariable int observingUserId, @PathVariable int observedUserId) {
        User observingUser = userService.getUserById(observingUserId).orElseThrow(() -> new RuntimeException("User not found"));
        User observedUser = userService.getUserById(observedUserId).orElseThrow(() -> new RuntimeException("User not found"));
        userObservedAccountService.unfollowUser(observingUser, observedUser);
    }
}