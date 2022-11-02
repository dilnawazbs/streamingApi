package com.relay.iot.producer.simulator.api.stream.producer;

import com.relay.iot.producer.simulator.api.model.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class EventProducer {

    private static final Logger log = LoggerFactory.getLogger(EventProducer.class);

    private final StreamBridge streamBridge;

    public EventProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }
    
    public Mono<Event> sendEvent(Event event) {
        Message<Event> message = MessageBuilder.withPayload(event)
                .setHeader("partitionKey", event.getId())
                .build();
        streamBridge.send("iot-data", message);
        return Mono.just(event);
    }
}
