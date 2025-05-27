package com.epam.java_advanced_training.metrics;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

  @Override
  public Health health() {
    boolean databaseUp = checkDatabaseConnection();

    if (databaseUp) {
      return Health.up()
          .withDetail("database", "Database is running")
          .withDetail("customMetric", 123)
          .build();
    } else {
      return Health.down()
          .withDetail("database", "Database is down")
          .withDetail("customMetric", 0)
          .build();
    }
  }

  private boolean checkDatabaseConnection() {
    return true;
  }
}
