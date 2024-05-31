package com.example.gamerise.api.controller;

import com.example.gamerise.api.model.Game;
import com.example.gamerise.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/game")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/get")
    public Game getGameById(@RequestParam int gameId) {
        Optional<Game> game = gameService.getGameById(gameId);
        return game.orElse(null);
    }

    @GetMapping("/cover/{filename:.+}")
    public ResponseEntity<Resource> serveCover(@PathVariable String filename) throws IOException {
        Resource file = new ClassPathResource("static/images/" + filename);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(file);
    }

    @GetMapping("/all")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Game addGame(@RequestPart("game") Game game, @RequestPart("cover") MultipartFile coverImage) {
        try {
            return gameService.addGame(game, coverImage);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save cover image", e);
        }
    }
    @GetMapping("/search")
    public List<Game> searchGames(@RequestParam String query) {
        return gameService.searchGames(query);
    }
}
