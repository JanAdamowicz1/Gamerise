package com.example.gamerise.api.controller;

import com.example.gamerise.api.model.UserActivity;
import com.example.gamerise.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activity")
public class UserActivityController {
    private final UserActivityService userActivityService;

    @Autowired
    public UserActivityController(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @PostMapping("/create")
    public String createActivity(@RequestParam int userId, @RequestParam int gameId, @RequestParam String activityType) {
        return "Activity created";
    }

    @GetMapping("/get")
    public UserActivity getActivityById(@RequestParam int activityId) {
        Optional userActivity = userActivityService.getActivityById(activityId);
        if (userActivity.isPresent()) {
            return (UserActivity) userActivity.get();
        } else {
            return null;
        }
    }

    @GetMapping("/all")
    public List<UserActivity> getAllActivities() {
        Optional activityList = userActivityService.getAllActivities();
        if (activityList.isPresent()) {
            return (List<UserActivity>) activityList.get();
        } else {
            return null;
        }
    }

    @DeleteMapping("/{activityId}/delete")
    public String deleteActivity(@PathVariable int activityId) {
        return "Activity deleted";
    }
}