package com.example.gamerise.api.model;

import com.example.gamerise.api.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user_game")
public class UserGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_game_id")
    private int userGameId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "shelf_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Shelf shelf;

    @OneToMany(mappedBy = "userGame", cascade = CascadeType.ALL)
    private List<UserActivity> userActivities;

    @Transient
    private Integer userRating; // Pole, które nie będzie przechowywane w bazie danych

    public UserGame() {
    }

    public UserGame(User user, Game game, Shelf shelf) {
        this.user = user;
        this.game = game;
        this.shelf = shelf;
    }

    public int getUserGameId() {
        return userGameId;
    }

    public void setUserGameId(int userGameId) {
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
    public Integer getUserRating() {
        return userRating;
    }

    public void setUserRating(Integer userRating) {
        this.userRating = userRating;
    }
}