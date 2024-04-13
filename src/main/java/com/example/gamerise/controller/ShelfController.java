package com.example.gamerise.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shelf")
public class ShelfController {
    @PostMapping("/{shelfId}/add")
    public String addGameToShelf(@PathVariable String shelfId, @RequestParam String userId, @RequestParam String gameId) {
        return "Game added to shelfName";
    }

    @DeleteMapping("/{shelfId}/remove")
    public String removeGameFromShelf(@PathVariable String shelfId, @RequestParam String userId, @RequestParam String gameId) {
        return "Game removed from shelfName";
    }

    @GetMapping("/{shelfId}")
    public String getGamesOnShelf(@PathVariable String shelfId, @RequestParam String userId) {
        return "List of games on shelfName";
    }
}