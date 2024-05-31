package com.example.gamerise.repository;

import com.example.gamerise.api.model.User;
import com.example.gamerise.api.model.UserObservedAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserObservedAccountRepository extends JpaRepository<UserObservedAccount, Integer> {
    @Query("SELECT uoa.observingUser.userId, uoa.observingUser.nickname FROM UserObservedAccount uoa WHERE uoa.observedUser.userId = :userId")
    List<Object[]> findObservingUsersByObservedUserId(int userId);

    @Query("SELECT uoa.observedUser.userId, uoa.observedUser.nickname FROM UserObservedAccount uoa WHERE uoa.observingUser.userId = :userId")
    List<Object[]> findObservedUsersByObservingUserId(int userId);

    Optional<UserObservedAccount> findByObservingUserAndObservedUser(User observingUser, User observedUser);

    List<UserObservedAccount> findByObservingUser_UserId(int userId);
}