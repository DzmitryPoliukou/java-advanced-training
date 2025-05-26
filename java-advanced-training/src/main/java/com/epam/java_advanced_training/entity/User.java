package com.epam.java_advanced_training.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String email;
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role = Role.USER;

  public enum Role {
    USER, ADMIN
  }

  public User() {
  }

  public User(CreateUserRequest request) {
    this.name = request.getName();
  }
}
