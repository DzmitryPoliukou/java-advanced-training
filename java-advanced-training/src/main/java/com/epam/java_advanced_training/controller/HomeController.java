package com.epam.java_advanced_training.controller;

import com.epam.java_advanced_training.entity.CreateUserRequest;
import com.epam.java_advanced_training.entity.User;
import com.epam.java_advanced_training.service.UserService;
import java.awt.desktop.UserSessionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

  private final UserService userService;

  @GetMapping("/")
  public String home() {
    return "Welcome to the Java Advanced Training!";
  }

  @GetMapping("/secured")
  public String secured() {
    return "This is a secured endpoint for admin!";
  }

  @GetMapping("/register-user")
  public User registerUser(@AuthenticationPrincipal OAuth2User jwt) {
    var createRequest = new CreateUserRequest();
    createRequest.setName(jwt.getName());
    var newUser = userService.createUser(createRequest);
    return newUser;
  }

}
