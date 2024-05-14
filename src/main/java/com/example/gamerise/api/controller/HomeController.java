package com.example.gamerise.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home")
public class HomeController {
    @GetMapping("/activities/{userId}")
    public String getFriendActivities(@PathVariable int userId) {
        return "List of friend activities for user " + userId;
    }
}
