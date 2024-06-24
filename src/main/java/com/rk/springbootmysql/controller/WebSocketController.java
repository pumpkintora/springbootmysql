package com.rk.springbootmysql.controller;

import com.rk.springbootmysql.service.KafkaConsumerService;
import com.rk.springbootmysql.service.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class WebSocketController {
    
    private final KafkaProducerService kafkaProducerService;
    private final KafkaConsumerService kafkaConsumerService;
    public WebSocketController(KafkaProducerService kafkaProducerService, KafkaConsumerService kafkaConsumerService) {
        this.kafkaProducerService = kafkaProducerService;
        this.kafkaConsumerService = kafkaConsumerService;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public void sendMessage(@Payload String message) {
        // Send the message to Kafka
        kafkaProducerService.sendMessage("my-topic", message);
    }

//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/public")
//    public void addUser(@Payload String message, SimpMessageHeaderAccessor headerAccessor){
//        log.info("User added: {}", message.getSender());
//        if (headerAccessor != null && headerAccessor.getSessionAttributes() != null) {
//            headerAccessor.getSessionAttributes().put("username", message.getSender());
//        } else {
//            log.error("headerAccessor or session attributes is null.");
//        }
//        kafkaProducerService.sendMessage("my-topic", message);
//    }
//    @MessageMapping("/chat.removeUser")
//    @SendTo("/topic/public")
//    public void removeUser(@Payload String message, SimpMessageHeaderAccessor headerAccessor) {
//        log.info("User disconnected: {}", message.getSender());
//        kafkaProducerService.sendMessage("my-topic", message);
//        if (headerAccessor != null && headerAccessor.getSessionAttributes() != null) {
//            headerAccessor.getSessionAttributes().remove("username");
//        } else {
//            log.error("headerAccessor or session attributes is null.");
//        }
//    }
    @GetMapping("/api/chat")
    public List<String> getChatMessages() {
        return kafkaConsumerService.getChatMessages();
    }
}