package com.epam.java_advanced_training.service;

import com.epam.java_advanced_training.entity.CreateUserRequest;
import com.epam.java_advanced_training.entity.User;
import com.epam.java_advanced_training.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public User createUser(CreateUserRequest request) {
    return userRepository.findByName(request.getName()).orElse(userRepository.save(new User(request)));
  }

}
