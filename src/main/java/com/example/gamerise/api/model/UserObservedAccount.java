package com.example.gamerise.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_observed_account")
public class UserObservedAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_observed_account_id")
    private int userObservedAccountId;

    @ManyToOne
    @JoinColumn(name = "observing_user_id")
    private User observingUser;

    @ManyToOne
    @JoinColumn(name = "observed_user_id")
    private User observedUser;

    public UserObservedAccount() {
    }

    public UserObservedAccount(User observingUser, User observedUser) {
        this.observingUser = observingUser;
        this.observedUser = observedUser;
    }

    public int getUserObservedAccountId() {
        return userObservedAccountId;
    }

    public void setUserObservedAccountId(int userObservedAccountId) {
        this.userObservedAccountId = userObservedAccountId;
    }

    public User getObservingUser() {
        return observingUser;
    }

    public void setObservingUser(User observingUser) {
        this.observingUser = observingUser;
    }

    public User getObservedUser() {
        return observedUser;
    }

    public void setObservedUser(User observedUser) {
        this.observedUser = observedUser;
    }
}