package com.example.gamerise.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_activity")
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_activity_id")
    private int userActivityId;

    @ManyToOne
    @JoinColumn(name = "user_game_id")
    private UserGame userGame;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "user_rating")
    private Integer userRating;

    @Column(name = "activity_type")
    private String activityType;

    @Column(name = "activity_date")
    private LocalDate activityDate;

    public UserActivity() {
    }

    public UserActivity(UserGame userGame, Integer likes, Integer userRating, String activityType, LocalDate activityDate) {
        this.userGame = userGame;
        this.likes = likes;
        this.userRating = userRating;
        this.activityType = activityType;
        this.activityDate = activityDate;
    }

    public int getUserActivityId() {
        return userActivityId;
    }

    public void setUserActivityId(int userActivityId) {
        this.userActivityId = userActivityId;
    }

    public UserGame getUserGame() {
        return userGame;
    }

    public void setUserGame(UserGame userGame) {
        this.userGame = userGame;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getUserRating() {
        return userRating;
    }

    public void setUserRating(Integer userRating) {
        this.userRating = userRating;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
    public LocalDate getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDate activityDate) {
        this.activityDate = activityDate;
    }

}