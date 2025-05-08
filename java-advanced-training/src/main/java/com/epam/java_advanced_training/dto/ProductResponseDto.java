package com.epam.java_advanced_training.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
  private Long id;
  private String name;
  private Long price;
}
