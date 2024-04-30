package com.rk.springbootmysql.service;


import ch.qos.logback.classic.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
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

    public List<String> getChatMessages() {
        // Implementasikan logika untuk mengembalikan daftar pesan dari Kafka
        return new ArrayList<>(chatMessages);
    }
}
