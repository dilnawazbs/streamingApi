package com.relay.iot.producer.simulator.api.model.event;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.springframework.lang.Nullable;

public interface Event {
  Long getId();

  BigDecimal getValue();

  OffsetDateTime getTimestamp();

  String getType();

  String getName();

  @Nullable
  Long getClusterId();
}
