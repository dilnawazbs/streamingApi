package com.relay.iot.producer.simulator.api.service;

import com.relay.iot.producer.simulator.api.controller.events.request.IotEventRequest;
import com.relay.iot.producer.simulator.api.mapper.IotEventMapper;
import com.relay.iot.producer.simulator.api.model.event.Event;
import com.relay.iot.producer.simulator.api.stream.producer.EventProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;

import java.time.Duration;
import java.util.List;

@Service
public class EventService {

    private static final Logger log = LoggerFactory.getLogger(EventService.class);

    private final EventProducer eventProducer;
    private final IotEventMapper iotEventMapper;

    public EventService(EventProducer eventProducer, IotEventMapper iotEventMapper) {
        this.eventProducer = eventProducer;
        this.iotEventMapper = iotEventMapper;
    }

    public ParallelFlux<Object> processAllEvents(List<IotEventRequest> events) {
        return Flux.fromIterable(events).parallel(events.size()).map(event -> processEvent(iotEventMapper.fromRequest(event), event.getTotal(), event.getHeartBeat()));
    }

    public Flux<Event> processEvent(Event event, Integer total, Integer heartBeat) {
        return Flux.range(0, total).delayElements(Duration.ofSeconds(heartBeat))
            .flatMap(processElement -> eventProducer.sendEvent(event));
    }
}
