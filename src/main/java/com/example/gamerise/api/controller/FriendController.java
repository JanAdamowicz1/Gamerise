package com.example.gamerise.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/friend")
public class FriendController {

    @PostMapping("/add")
    public String addFriend(@RequestParam int userId, @RequestParam int friendId) {
        return "Friend added";
    }

    @DeleteMapping("/{userId}/delete/{friendId}")
    public String deleteFriend(@PathVariable int userId, @PathVariable int friendId) {
        return "Friend deleted";
    }

    @GetMapping("/list/{userId}")
    public String getFriendList(@PathVariable int userId) {
        return "List of friends for user " + userId;
    }
}