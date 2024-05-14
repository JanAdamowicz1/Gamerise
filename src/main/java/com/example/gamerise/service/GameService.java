package com.example.gamerise.service;

import com.example.gamerise.api.model.Game;
import com.example.gamerise.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Optional<Game> getGameById(int id) {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isPresent()) {
            return game;
        } else {
            return Optional.empty();
        }
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

    public Game addGame(Game game) {
        return null;
    }

    public Game updateGame(int id, Game game) {
        return null;
    }

    public void deleteGame(int id) {
    }
}