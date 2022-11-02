package com.relay.iot.producer.simulator.api.model.event;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.apache.commons.lang3.RandomUtils;
import org.immutables.value.Value;

@Value.Modifiable
public interface IotEvent extends Event {
  @Override
  @Value.Default
  default OffsetDateTime getTimestamp() {
    return OffsetDateTime.now(ZoneOffset.UTC);
  }

  @Override
  @Value.Default
  default BigDecimal getValue() {
    return BigDecimal.valueOf(RandomUtils.nextDouble(25.00D, 100.00D));
  }
}
