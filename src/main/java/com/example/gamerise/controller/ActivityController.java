package com.example.gamerise.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @PostMapping("/create")
    public String createActivity(@RequestParam String userId, @RequestParam String gameId, @RequestParam String activityType) {
        return "Activity created";
    }

    @GetMapping("/{activityId}")
    public String getActivity(@PathVariable String activityId) {
        return "Activity details";
    }

    @DeleteMapping("/{activityId}/delete")
    public String deleteActivity(@PathVariable String activityId) {
        return "Activity deleted";
    }
}