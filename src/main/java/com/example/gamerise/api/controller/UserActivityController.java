package com.example.gamerise.api.controller;

import com.example.gamerise.api.model.UserActivity;
import com.example.gamerise.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public UserActivity createActivity(@RequestBody UserActivity userActivity) {
        return userActivityService.addActivity(userActivity);
    }

    @PutMapping("/update/{activityId}")
    public UserActivity updateActivity(@PathVariable int activityId, @RequestBody UserActivity userActivity) {
        if (activityId != userActivity.getUserActivityId()) {
            throw new IllegalArgumentException("Activity ID doesn't match!");
        }
        return userActivityService.updateActivity(userActivity);
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

    @GetMapping("/observed")
    public List<UserActivity> getActivitiesByObservedUsers(@RequestParam int userId) {
        return userActivityService.getActivitiesByObservedUsers(userId);
    }

    @PostMapping("/{activityId}/like")
    public ResponseEntity<UserActivity> likeActivity(@PathVariable int activityId, @RequestParam int userId) {
        try {
            UserActivity updatedActivity = userActivityService.toggleLike(activityId, userId);
            return ResponseEntity.ok(updatedActivity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @GetMapping("/{activityId}/liked")
    public ResponseEntity<Map<String, Boolean>> isLikedByUser(@PathVariable int activityId, @RequestParam int userId) {
        boolean liked = userActivityService.isLikedByUser(activityId, userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("liked", liked);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{activityId}/delete")
    public String deleteActivity(@PathVariable int activityId) {
        return "Activity deleted";
    }
}