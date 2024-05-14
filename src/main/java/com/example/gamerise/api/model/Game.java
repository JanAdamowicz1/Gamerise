package com.example.gamerise.api.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private int gameId;

    @Column(name = "game_name")
    private String gameName;

    @Column(name = "cover")
    private String cover;

    @OneToMany(mappedBy = "game")
    private List<UserGame> userGames;

    public Game() {
    }

    public Game(String gameName, String cover) {
        this.gameName = gameName;
        this.cover = cover;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}