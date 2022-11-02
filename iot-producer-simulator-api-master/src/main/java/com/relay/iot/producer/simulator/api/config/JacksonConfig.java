package com.relay.iot.producer.simulator.api.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;

@Configuration
public class JacksonConfig {

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper()
      .registerModule(new JavaTimeModule())
      .setSerializationInclusion(JsonInclude.Include.NON_NULL)
      .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  @Bean
  Jackson2JsonEncoder jackson2JsonEncoder(ObjectMapper mapper) {
    return new Jackson2JsonEncoder(mapper);
  }

  @Bean
  Jackson2JsonDecoder jackson2JsonDecoder(ObjectMapper mapper) {
    return new Jackson2JsonDecoder(mapper);
  }
}
