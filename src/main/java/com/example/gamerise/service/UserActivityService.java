package com.example.gamerise.service;

import com.example.gamerise.api.model.UserActivity;
import com.example.gamerise.repository.UserActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserActivityService {
    private final UserActivityRepository userActivityRepository;

    public UserActivityService(UserActivityRepository userActivityRepository) {
        this.userActivityRepository = userActivityRepository;
    }
    public Optional<UserActivity> getActivityById(int activityId) {
        return userActivityRepository.findById(activityId);
    }

    public Optional<List<UserActivity>> getAllActivities() {
        return Optional.of(userActivityRepository.findAll());
    }
}
