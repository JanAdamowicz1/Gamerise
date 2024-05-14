package com.example.gamerise.repository;

import com.example.gamerise.api.model.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {
}
