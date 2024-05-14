package com.example.gamerise.service;

import com.example.gamerise.api.model.Game;
import com.example.gamerise.api.model.Shelf;
import com.example.gamerise.api.model.User;
import com.example.gamerise.api.model.UserGame;
import com.example.gamerise.repository.UserGameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserGameService {

    private final UserGameRepository userGameRepository;

    @Autowired
    public UserGameService(UserGameRepository userGameRepository) {
        this.userGameRepository = userGameRepository;
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
        return userGameRepository.save(userGame);
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
        return userGameRepository.save(existingUserGame);
    }
}