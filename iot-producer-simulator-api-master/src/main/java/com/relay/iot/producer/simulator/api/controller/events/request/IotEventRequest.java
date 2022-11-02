package com.relay.iot.producer.simulator.api.controller.events.request;

import static com.relay.iot.producer.simulator.api.model.constant.EventsConstant.*;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.validation.constraints.*;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@Value.Immutable
@Value.Style(builder = "new")
@JsonDeserialize(builder = ImmutableIotEventRequest.Builder.class)
public interface IotEventRequest {
  @Positive(message = "{invalid.event.request.total.id}")
  @NotNull(message = "{mandatory.event.request.id}")
  @Nullable
  Long getId();

  @Nullable
  @NotBlank(message = "{mandatory.request.name}")
  String getName();

  @NotNull(message = "{mandatory.request.type}")
  @Nullable
  String getType();

  @Max(value = MAX_HEART_BEAT, message = "{invalid.event.total.heartBeat}")
  @Min(value = MIN_HEART_BEAT, message = "{invalid.event.total.heartBeat}")
  @Value.Default
  default Integer getHeartBeat() {
    return DEFAULT_HEART_BEAT;
  }

  @Positive(message = "{invalid.request.total.positive}")
  @NotNull(message = "{mandatory.request.total}")
  @Nullable
  Integer getTotal();

  @Nullable
  Long getClusterId();
}
