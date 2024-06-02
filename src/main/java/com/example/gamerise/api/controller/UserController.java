package com.example.gamerise.api.controller;

import com.example.gamerise.api.dto.UserDto;
import com.example.gamerise.api.model.Role;
import com.example.gamerise.api.model.User;
import com.example.gamerise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public User getUserById(@RequestParam int userId){
        Optional user = userService.getUserById(userId);
        if (user.isPresent()) {
            return (User) user.get();
        } else {
            return null;
        }
    }

    @GetMapping("/me")
    public User getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.findByUsername(userDetails.getUsername());
    }

    @GetMapping("/role")
    public Role getUserRole(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        return user.getRole();
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        Optional userList = userService.getAllUsers();
        if (userList.isPresent()) {
            return (List<User>) userList.get();
        } else {
            return null;
        }
    }

    @GetMapping("/search")
    public List<UserDto> searchUsers(@RequestParam String query) {
        return userService.searchUsers(query);
    }


    @PostMapping("/uploadProfilePicture")
    public ResponseEntity<String> uploadProfilePicture(@RequestParam("file") MultipartFile file,
                                                       @AuthenticationPrincipal UserDetails userDetails) {
        try {
            userService.saveProfilePicture(userDetails.getUsername(), file);
            User user = userService.findByUsername(userDetails.getUsername());
            return ResponseEntity.ok(user.getProfilePicture());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating profile picture");
        }
    }

    @GetMapping("/profilePicture")
    public ResponseEntity<String> getProfilePicture(@RequestParam int userId) {
        Optional<User> optionalUser = userService.getUserById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return ResponseEntity.ok(user.getProfilePicture());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PostMapping("/check")
    public ResponseEntity<Map<String, Boolean>> checkUserExists(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String nickname = request.get("nickname");

        boolean emailExists = userService.emailExists(email);
        boolean nicknameExists = userService.nicknameExists(nickname);

        Map<String, Boolean> response = new HashMap<>();
        response.put("emailExists", emailExists);
        response.put("nicknameExists", nicknameExists);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
