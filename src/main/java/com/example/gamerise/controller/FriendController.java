package com.example.gamerise.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/friend")
public class FriendController {

    @PostMapping("/add")
    public String addFriend(@RequestParam String userId, @RequestParam String friendId) {
        return "Friend added";
    }

    @DeleteMapping("/{userId}/delete/{friendId}")
    public String deleteFriend(@PathVariable String userId, @PathVariable String friendId) {
        return "Friend deleted";
    }

    @GetMapping("/list/{userId}")
    public String getFriendList(@PathVariable String userId) {
        return "List of friends for user " + userId;
    }
}