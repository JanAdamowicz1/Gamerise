package com.example.gamerise.repository;

import com.example.gamerise.api.model.UserActivity;
import com.example.gamerise.api.model.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {
    Optional<UserActivity> findByUserGameAndActivityType(UserGame userGame, Integer activityType);

    List<UserActivity> findAllByUserGame_User_UserId(int userId);
}
