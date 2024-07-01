package com.rk.springbootmysql.service;


import ch.qos.logback.classic.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumerService {

    private final List<String> chatMessages = new ArrayList<>();

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "my-topic", groupId = "my-group-id")
    public void listen(String message) {
        System.out.println("Received message: " + message);
        chatMessages.add(message);
        messagingTemplate.convertAndSend("/topic/public", message);
    }
}
