package com.epam.java_advanced_training;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldRunner implements CommandLineRunner {

  @Override
  public void run(String... args) {
    System.out.println("Hello, World!");
  }
}
