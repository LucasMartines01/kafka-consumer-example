package com.lucasmartines.kafka.consumer;

import com.lucasmartines.kafka.dto.Customer;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class KafkaMessageListener {

    Logger logger = Logger.getLogger(KafkaMessageListener.class.getName());

    @KafkaListener(topics = "topic-demo2", groupId = "group_id")
    public void listen(String message) {
        logger.info("Received message: " + message);
    }

    @RetryableTopic(attempts = "3", backoff = @Backoff(delay = 1000, maxDelay = 10000, multiplier = 2))
    @KafkaListener(topics = "topic-demo", groupId = "group_id")
    public void listenCustomer(Customer customer) {
        logger.info("Received message: " + customer.toString());
    }

    @DltHandler
    public void listenDLT(Customer customer, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) {
        logger.info("Received message from DLT: " + customer.getName() + " from topic: " + topic + " with offset: " + offset);
    }
}
