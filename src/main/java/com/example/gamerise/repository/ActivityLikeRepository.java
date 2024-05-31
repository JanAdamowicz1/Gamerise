package com.example.gamerise.repository;

import com.example.gamerise.api.model.ActivityLike;
import com.example.gamerise.api.model.User;
import com.example.gamerise.api.model.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivityLikeRepository extends JpaRepository<ActivityLike, Integer> {
    Optional<ActivityLike> findByUserActivityAndUser(UserActivity userActivity, User user);
    int countByUserActivity(UserActivity userActivity);
}
