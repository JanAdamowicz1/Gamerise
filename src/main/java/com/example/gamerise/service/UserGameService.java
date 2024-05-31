package com.example.gamerise.service;

import com.example.gamerise.api.model.*;
import com.example.gamerise.repository.UserGameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserGameService {

    private final UserGameRepository userGameRepository;
    private final UserActivityService userActivityService;

    @Autowired
    public UserGameService(UserGameRepository userGameRepository, UserActivityService userActivityService) {
        this.userGameRepository = userGameRepository;
        this.userActivityService = userActivityService;
    }

    public Optional<UserGame> getUserGameById(int userGameId) {
        return userGameRepository.findById(userGameId);
    }

    public Optional<List<UserGame>> getUserGamesByUserId(int userId) {
        return Optional.of(userGameRepository.findByUser_UserId(userId));
    }
    public Optional<List<UserGame>> getAllUserGames() {
        return Optional.of(userGameRepository.findAll());
    }

    public Optional<List<Game>> getGamesByUserAndShelf(User user, Shelf shelf) {
        return userGameRepository.findGamesByUserAndShelf(user, shelf);
    }
    public UserGame addUserGame(UserGame userGame) {
        UserGame savedUserGame = userGameRepository.save(userGame);
        savedUserGame.setUserRating(userGame.getUserRating());
        handleUserActivity(savedUserGame);
        return savedUserGame;
    }

    @Transactional
    public void deleteUserGame(User user, Game game) {
        userGameRepository.deleteByUserAndGame(user, game);
    }

    @Transactional
    public UserGame updateUserGame(int userId, int gameId, UserGame userGame) {
        UserGame existingUserGame = userGameRepository.findByUserAndGame(userId, gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserGame not found"));
        existingUserGame.setShelf(userGame.getShelf());
        existingUserGame.setUserRating(userGame.getUserRating());
        UserGame savedUserGame = userGameRepository.save(existingUserGame);
        handleUserActivity(savedUserGame);
        return savedUserGame;
    }

    private void handleUserActivity(UserGame savedUserGame) {
        UserActivity userActivity = new UserActivity();
        userActivity.setUserGame(savedUserGame);
        userActivity.setLikes(0);
        userActivity.setActivityType(savedUserGame.getShelf().getShelfId());
        userActivity.setActivityDate(LocalDate.now());
        userActivity.setUserRating(savedUserGame.getUserRating());

        Optional<UserActivity> existingUserActivity = userActivityService.getActivityByUserGameAndActivityType(savedUserGame, savedUserGame.getShelf().getShelfId());

        if (existingUserActivity.isPresent()) {
            UserActivity existingActivity = existingUserActivity.get();
            existingActivity.setActivityDate(LocalDate.now());
            existingActivity.setLikes(0);
            existingActivity.setUserRating(savedUserGame.getUserRating());
            userActivityService.updateActivity(existingActivity);
        } else {
            userActivityService.addActivity(userActivity);
        }
    }
}