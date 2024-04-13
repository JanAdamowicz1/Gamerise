package com.example.gamerise.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_activity")
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_activity_id")
    private Long userActivityId;

    @ManyToOne
    @JoinColumn(name = "user_game_id")
    private UserGame userGame;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "user_rating")
    private Integer userRating;

    @Column(name = "activity_type")
    private String activityType;

    public UserActivity() {
    }

    public UserActivity(Long userActivityId, UserGame userGame, Integer likes, Integer userRating, String activityType) {
        this.userActivityId = userActivityId;
        this.userGame = userGame;
        this.likes = likes;
        this.userRating = userRating;
        this.activityType = activityType;
    }

    public Long getUserActivityId() {
        return userActivityId;
    }

    public void setUserActivityId(Long userActivityId) {
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
}