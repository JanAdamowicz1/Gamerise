package com.example.gamerise.service;

import com.example.gamerise.api.model.*;
import com.example.gamerise.repository.ActivityLikeRepository;
import com.example.gamerise.repository.UserActivityRepository;
import com.example.gamerise.repository.UserObservedAccountRepository;
import com.example.gamerise.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserActivityService {
    private final UserActivityRepository userActivityRepository;
    private final UserRepository userRepository;
    private final ActivityLikeRepository activityLikeRepository;
    private final UserObservedAccountRepository userObservedAccountRepository;

    public UserActivityService(UserActivityRepository userActivityRepository, UserObservedAccountRepository userObservedAccountRepository, UserRepository userRepository, ActivityLikeRepository activityLikeRepository) {
        this.userActivityRepository = userActivityRepository;
        this.userObservedAccountRepository = userObservedAccountRepository;
        this.activityLikeRepository = activityLikeRepository;
        this.userRepository = userRepository;
    }
    public Optional<UserActivity> getActivityById(int activityId) {
        return userActivityRepository.findById(activityId);
    }

    public Optional<List<UserActivity>> getAllActivities() {
        return Optional.of(userActivityRepository.findAll());
    }

    public Optional<UserActivity> getActivityByUserGameAndActivityType(UserGame userGame, Integer activityType) {
        return userActivityRepository.findByUserGameAndActivityType(userGame, activityType);
    }

    public UserActivity addActivity(UserActivity userActivity) {
        return userActivityRepository.save(userActivity);
    }

    public UserActivity updateActivity(UserActivity userActivity) {
        return userActivityRepository.save(userActivity);
    }

    public List<UserActivity> getActivitiesByObservedUsers(int userId) {
        List<UserObservedAccount> observedAccounts = userObservedAccountRepository.findByObservingUser_UserId(userId);
        List<UserActivity> activities = new ArrayList<>();

        for (UserObservedAccount observedAccount : observedAccounts) {
            List<UserActivity> userActivities = userActivityRepository.findAllByUserGame_User_UserId(observedAccount.getObservedUser().getUserId());
            activities.addAll(userActivities);
        }

        return activities;
    }

    public UserActivity toggleLike(int activityId, int userId) {
        UserActivity userActivity = userActivityRepository.findById(activityId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        Optional<ActivityLike> existingLike = activityLikeRepository.findByUserActivityAndUser(userActivity, user);

        if (existingLike.isPresent()) {
            activityLikeRepository.delete(existingLike.get());
        } else {
            ActivityLike newLike = new ActivityLike(userActivity, user);
            activityLikeRepository.save(newLike);
        }

        int likesCount = activityLikeRepository.countByUserActivity(userActivity);
        userActivity.setLikes(likesCount);

        return userActivityRepository.save(userActivity);
    }

    public boolean isLikedByUser(int activityId, int userId) {
        UserActivity userActivity = userActivityRepository.findById(activityId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        return activityLikeRepository.findByUserActivityAndUser(userActivity, user).isPresent();
    }
}
