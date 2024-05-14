package com.example.gamerise.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shelf")
public class ShelfController {
    @PostMapping("/{shelfId}/add")
    public String addGameToShelf(@PathVariable int shelfId, @RequestParam int userId, @RequestParam int gameId) {
        return "Game added to shelfName";
    }

    @DeleteMapping("/{shelfId}/remove")
    public String removeGameFromShelf(@PathVariable int shelfId, @RequestParam int userId, @RequestParam int gameId) {
        return "Game removed from shelfName";
    }

    @GetMapping("/{shelfId}")
    public String getGamesOnShelf(@PathVariable int shelfId, @RequestParam int userId) {
        return "List of games on shelfName";
    }
}