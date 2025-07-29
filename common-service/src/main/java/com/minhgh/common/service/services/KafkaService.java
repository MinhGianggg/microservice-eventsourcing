package com.minhgh.common.service.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaService<T> {

    private final KafkaTemplate<String, T> kafkaTemplate;

    public void send(String topic, T message) {
        kafkaTemplate.send(topic, message);
        log.debug("Message {} send to topic {}", message, topic);
    }
}
