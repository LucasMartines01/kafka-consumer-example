package com.lucasmartines.kafka.consumer;

import com.lucasmartines.kafka.dto.Customer;
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

    @KafkaListener(topics = "topic-demo", groupId = "group_id")
    public void listenCustomer(Customer customer) {
        logger.info("Received message: " + customer.toString());
    }

}
