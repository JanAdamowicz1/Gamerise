package com.example.gamerise.service;

import com.example.gamerise.api.dto.UserDto;
import com.example.gamerise.api.model.User;
import com.example.gamerise.repository.UserGameRepository;
import com.example.gamerise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Path rootLocation = Paths.get("src/main/resources/static/profile-pictures");


    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }
    public Optional<User> getUserById(int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user;
        } else {
            return Optional.empty();
        }
    }

    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    public Optional<List<User>> getAllUsers() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(userList);
        }
    }

    public List<UserDto> searchUsers(String query) {
        return userRepository.findByNicknameContaining(query).stream()
                .map(user -> new UserDto(user.getUserId(), user.getNickname()))
                .collect(Collectors.toList());
    }

    public void saveProfilePicture(String username, MultipartFile file) throws IOException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String filename = user.getUserId() + "_" + file.getOriginalFilename();
            System.out.println("Saving file: " + filename);
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            user.setProfilePicture(filename);
            userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean nicknameExists(String nickname) {
        return userRepository.findByNickname(nickname).isPresent();
    }


}