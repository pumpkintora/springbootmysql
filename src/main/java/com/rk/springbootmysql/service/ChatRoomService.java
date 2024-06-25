package com.rk.springbootmysql.service;
import com.rk.springbootmysql.model.chat.ChatRoom;
import com.rk.springbootmysql.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public ChatRoom createChatRoom(String name) {
        ChatRoom existingChatRoom = chatRoomRepository.findByName(name);
        if (existingChatRoom != null) {
            throw new IllegalArgumentException("Chat room with the same name already exists.");
        }
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(name);
        return chatRoomRepository.save(chatRoom);
    }

    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    public List<ChatRoom> getChatRoomsByUserId(Long userId) { return chatRoomRepository.findByUserId(userId); }
}