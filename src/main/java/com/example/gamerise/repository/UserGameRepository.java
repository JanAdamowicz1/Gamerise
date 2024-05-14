package com.example.gamerise.repository;

import com.example.gamerise.api.model.Game;
import com.example.gamerise.api.model.Shelf;
import com.example.gamerise.api.model.User;
import com.example.gamerise.api.model.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserGameRepository extends JpaRepository<UserGame, Integer> {
    @Query("SELECT ug.game FROM UserGame ug WHERE ug.user = :user AND ug.shelf = :shelf")
    Optional<List<Game>> findGamesByUserAndShelf(User user, Shelf shelf);

    void deleteByUserAndGame(User user, Game game);

    @Query("SELECT ug FROM UserGame ug WHERE ug.user.userId = :userId AND ug.game.gameId = :gameId")
    Optional<UserGame> findByUserAndGame(int userId, int gameId);

    List<UserGame> findByUser_UserId(int userId);
}
