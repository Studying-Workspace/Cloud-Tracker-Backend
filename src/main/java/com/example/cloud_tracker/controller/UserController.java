package com.example.cloud_tracker.controller;

import com.example.cloud_tracker.dto.UserDTO;
import com.example.cloud_tracker.dto.UserUpdateDTO;
import com.example.cloud_tracker.model.JwtResponse;
import com.example.cloud_tracker.model.User;
import com.example.cloud_tracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/test")
  public ResponseEntity<Object> test() {
    return ResponseEntity.status(HttpStatus.OK).body("Test");
  }

  @PostMapping("/signup")
  public ResponseEntity<User> signup(@RequestBody @Valid UserDTO userDTO) {
    User user = userService.register(userDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  // Todo : Invalid endpoint, authenticated endpoints shouldn't be redirected to
  // gh login page
  @PostMapping("/signin")
  public ResponseEntity<JwtResponse> login(@RequestBody UserDTO userDTO) {
    JwtResponse jwtResponse = userService.login(userDTO);
    return ResponseEntity.status(HttpStatus.OK).body(jwtResponse);
  }

  @GetMapping("/current-user/profile-picture")
  public ResponseEntity<String> getCurrentUserProfilePicture() {
    String profilePicture = userService.getCurrentUserProfilePicture();
    return ResponseEntity.ok(profilePicture);
  }

  @GetMapping("/current-user/name")
  public ResponseEntity<String> getCurrentUserName() {
    String userName = userService.getCurrentUserName();
    return ResponseEntity.ok(userName);
  }

  @GetMapping("/current-user/email")
  public ResponseEntity<String> getCurrentUserEmail() {
    String email = userService.getCurrentUserEmail();
    return ResponseEntity.ok(email);
  }

  @PutMapping("/edit-profile")
  public ResponseEntity<User> editProfile(@RequestBody UserUpdateDTO updateDTO){
    User user = userService.editProfile(updateDTO);
    return ResponseEntity.ok(user);
  }
}
