package com.lucasmartines.kafka.consumer.example.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class KafkaMessageListener {

    Logger logger = Logger.getLogger(KafkaMessageListener.class.getName());

    @KafkaListener(topics = "topic-demo2", groupId = "group_id")
    public void listen(String message) {
        logger.info("Received message: " + message);
    }

}
