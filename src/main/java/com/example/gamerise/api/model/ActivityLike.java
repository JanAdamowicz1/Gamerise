package com.example.gamerise.api.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "activity_like")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "activityLikeId")
public class ActivityLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_like_id")
    private int activityLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_activity_id")
    private UserActivity userActivity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ActivityLike() {}

    public ActivityLike(UserActivity userActivity, User user) {
        this.userActivity = userActivity;
        this.user = user;
    }

    public int getActivityLikeId() {
        return activityLikeId;
    }

    public void setActivityLikeId(int activityLikeId) {
        this.activityLikeId = activityLikeId;
    }

    public UserActivity getUserActivity() {
        return userActivity;
    }

    public void setUserActivity(UserActivity userActivity) {
        this.userActivity = userActivity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
