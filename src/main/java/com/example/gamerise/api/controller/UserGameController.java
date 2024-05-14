package com.example.gamerise.api.controller;

import com.example.gamerise.api.model.Game;
import com.example.gamerise.api.model.Shelf;
import com.example.gamerise.api.model.User;
import com.example.gamerise.api.model.UserGame;
import com.example.gamerise.service.GameService;
import com.example.gamerise.service.ShelfService;
import com.example.gamerise.service.UserGameService;
import com.example.gamerise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usergame")
public class UserGameController {
    private final UserGameService userGameService;
    private final UserService userService;
    private final ShelfService shelfService;
    private final GameService gameService;

    @Autowired
    public UserGameController(UserGameService userGameService, ShelfService shelfService, UserService userService, GameService gameService) {
        this.userGameService = userGameService;
        this.shelfService = shelfService;
        this.userService = userService;
        this.gameService = gameService;
    }

    @GetMapping("/{id}")
    public Optional<UserGame> getUserGameById(@PathVariable int id) {
        return userGameService.getUserGameById(id);
    }

    @GetMapping("/user/{userId}/shelf/{shelfId}")
    public Optional<List<Game>> getGamesByUserAndShelf(@PathVariable int userId, @PathVariable int shelfId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Shelf shelf = shelfService.getShelfById(shelfId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shelf not found"));
        return userGameService.getGamesByUserAndShelf(user, shelf);
    }

    @GetMapping("/get/user/{userId}")
    public Optional<List<UserGame>> getUserGamesByUserId(@PathVariable int userId) {
        return userGameService.getUserGamesByUserId(userId);
    }

    @GetMapping("/all")
    public Optional<List<UserGame>> getAllUserGames() {
        Optional userList = userGameService.getAllUserGames();
        if (userList.isPresent()) {
            return (Optional<List<UserGame>>) userList;
        } else {
            return Optional.empty();
        }
    }

    @PostMapping("/add")
    public UserGame addUserGame(@RequestBody UserGame userGame) {
        return userGameService.addUserGame(userGame);
    }

    @DeleteMapping("/delete/user/{userId}/game/{gameId}")
    public void deleteUserGame(@PathVariable int userId, @PathVariable int gameId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Game game = gameService.getGameById(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
        userGameService.deleteUserGame(user, game);
    }

    @PutMapping("/update/user/{userId}/game/{gameId}")
    public UserGame updateUserGame(@PathVariable int userId, @PathVariable int gameId, @RequestBody UserGame userGame) {
        return userGameService.updateUserGame(userId, gameId, userGame);
    }
}