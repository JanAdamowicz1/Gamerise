package com.example.gamerise.api.dto;

public class UserDto {
    private int userId;
    private String nickname;

    public UserDto(Integer userID, String nickname) {
        this.userId = userID;
        this.nickname = nickname;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}