package com.example.gamerise.service;

import com.example.gamerise.api.dto.UserDto;
import com.example.gamerise.api.model.User;
import com.example.gamerise.api.model.UserObservedAccount;
import com.example.gamerise.repository.UserObservedAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserObservedAccountService {
    private final UserObservedAccountRepository userObservedAccountRepository;

    @Autowired
    public UserObservedAccountService(UserObservedAccountRepository userObservedAccountRepository) {
        this.userObservedAccountRepository = userObservedAccountRepository;
    }

    public UserObservedAccount followUser(User observingUser, User observedUser) {
        UserObservedAccount userObservedAccount = new UserObservedAccount(observingUser, observedUser);
        return userObservedAccountRepository.save(userObservedAccount);
    }

    public List<UserDto> getObservingUsers(int userId) {
        return userObservedAccountRepository.findObservingUsersByObservedUserId(userId).stream()
                .map(objects -> new UserDto((Integer) objects[0], (String) objects[1]))
                .collect(Collectors.toList());
    }

    public List<UserDto> getObservedUsers(int userId) {
        return userObservedAccountRepository.findObservedUsersByObservingUserId(userId).stream()
                .map(objects -> new UserDto((Integer) objects[0], (String) objects[1]))
                .collect(Collectors.toList());
    }

    public void unfollowUser(User observingUser, User observedUser) {
        UserObservedAccount userObservedAccount = userObservedAccountRepository.findByObservingUserAndObservedUser(observingUser, observedUser)
                .orElseThrow(() -> new RuntimeException("Observation not found"));
        userObservedAccountRepository.delete(userObservedAccount);
    }


}