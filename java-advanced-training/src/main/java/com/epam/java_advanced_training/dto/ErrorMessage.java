package com.epam.java_advanced_training.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ErrorMessage {

  private final String message;
  private final String errorCode;
}
