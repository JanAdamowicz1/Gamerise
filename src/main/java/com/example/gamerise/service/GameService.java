package com.example.gamerise.service;

import com.example.gamerise.api.model.Game;
import com.example.gamerise.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final Path rootLocation = Paths.get("src/main/resources/static/images");

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    public Optional<Game> getGameById(int id) {
        return gameRepository.findById(id);
    }

    public List<Game> searchGames(String query) {
        return gameRepository.findByGameNameContainingIgnoreCase(query);
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public List<Game> getGamesByShelf(int shelfId) {
        return null;
    }

    public Game addGame(Game game, MultipartFile coverImage) throws IOException {
        if (coverImage != null && !coverImage.isEmpty()) {
            String imageName = saveCoverImage(coverImage);
            game.setCover(imageName);
        }
        return gameRepository.save(game);
    }

    private String saveCoverImage(MultipartFile coverImage) throws IOException {
        String imageName = coverImage.getOriginalFilename();
        Path imagePath = this.rootLocation.resolve(imageName);
        Files.copy(coverImage.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
        return imageName;
    }

    public Game updateGame(int id, Game game) {
        return null;
    }

    public void deleteGame(int id) {
    }
}
