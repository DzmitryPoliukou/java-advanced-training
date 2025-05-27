package com.epam.java_advanced_training.metrics;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "mycustom") //  /actuator/mycustom
public class MyCustomActuatorEndpoint {

  private final Map<String, Object> customData = new HashMap<>();

  public MyCustomActuatorEndpoint() {
    customData.put("app.startedAt", System.currentTimeMillis());
    customData.put("custom.metric", 42);
  }

  @ReadOperation
  public Map<String, Object> getCustomData() {
    customData.put("app.currentTime", System.currentTimeMillis());
    return customData;
  }


  @WriteOperation
  public Map<String, String> resetCustomMetric() {
    customData.put("custom.metric", 0);
    return Map.of("status", "SUCCESS", "message", "Metric has been reset");
  }

  @ReadOperation
  public Map<String, Object> getItemById(@Selector String id) {
    return Map.of("id", id, "value", "someValue");
  }
}
