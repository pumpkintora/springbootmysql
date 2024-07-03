package com.rk.springbootmysql.service;

import com.rk.springbootmysql.model.chat.ChatMessage;
import com.rk.springbootmysql.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public List<ChatMessage> getChatMessagesByChatroomId(Long chatroomId) {
        return chatMessageRepository.findByChatroomId(chatroomId);
    }

    public List<ChatMessage> getChatMessagesByUserId(Long userId) {
        return chatMessageRepository.findByUserId(userId);
    }
}
