package com.relay.iot.producer.simulator.api.mapper;

import com.relay.iot.producer.simulator.api.controller.events.request.IotEventRequest;
import com.relay.iot.producer.simulator.api.model.event.IotEvent;
import com.relay.iot.producer.simulator.api.model.event.ModifiableIotEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IotEventMapper {
  default IotEvent fromRequest(IotEventRequest request) {
    return ModifiableIotEvent
      .create()
      .setType(request.getType())
      .setName(request.getName())
      .setId(request.getId())
      .setClusterId(request.getClusterId());
  }
}
