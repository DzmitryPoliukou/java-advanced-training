package com.epam.java_advanced_training.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
  private String name;
  private String email;
}
