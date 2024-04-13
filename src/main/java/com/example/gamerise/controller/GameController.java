package com.example.gamerise.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/game")
public class GameController {
    @GetMapping("/{gameId}")
    public String getGameById(){
        return "Game";
    }
    @PostMapping("/add")
    public String addGameToUserList(@RequestParam String userId, @RequestParam String gameId) {
        return "Game added to user's list";
    }
    @GetMapping("/search")
    public String searchGames(@RequestParam String query) {
        return "List of games matching the query: " + query;
    }

}
