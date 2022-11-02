package com.relay.iot.producer.simulator.api.controller.events;

import com.relay.iot.producer.simulator.api.controller.events.request.IotEventRequest;
import com.relay.iot.producer.simulator.api.service.EventService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Validated
@RestController
@RequestMapping("/iot-events")
public class IotEventController {

  private static final Logger log = LoggerFactory.getLogger(
    IotEventController.class
  );
  private final EventService eventService;

  public IotEventController(EventService eventService) {
    this.eventService = eventService;
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Void> produceEvents(@Size(min = 1, max = 10, message = "{invalid.request.list.size}") @RequestBody List<@Valid IotEventRequest> request) {
    eventService
      .processAllEvents(request)
      .doOnSubscribe(subscription -> log.info("Received input request -> {}", request))
      .subscribe(null, throwable -> log.error("Failed to process the request " + request, throwable),
        () -> log.info("Process Ended for request -> {}", request)
      );
    return Mono.empty();
  }
}
