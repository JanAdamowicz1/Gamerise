package com.example.gamerise.repository;

import com.example.gamerise.api.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findByGameNameContainingIgnoreCase(String gameName);
}