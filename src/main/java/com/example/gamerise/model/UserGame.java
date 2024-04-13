package com.example.gamerise.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user_game")
public class UserGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_game_id")
    private Long userGameId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "shelf_id")
    private Shelf shelf;

    @OneToMany(mappedBy = "userGame")
    private List<UserActivity> userActivities;

    public UserGame() {
    }

    public UserGame(Long userGameId, User user, Game game, Shelf shelf) {
        this.userGameId = userGameId;
        this.user = user;
        this.game = game;
        this.shelf = shelf;
    }

    public Long getUserGameId() {
        return userGameId;
    }

    public void setUserGameId(Long userGameId) {
        this.userGameId = userGameId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }
}