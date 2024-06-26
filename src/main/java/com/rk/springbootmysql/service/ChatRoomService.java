package com.rk.springbootmysql.service;
import com.rk.springbootmysql.model.chat.ChatRoom;
import com.rk.springbootmysql.model.user.User;
import com.rk.springbootmysql.repository.ChatRoomRepository;
import com.rk.springbootmysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ChatRoomService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private UserRepository userRepository;

    public ChatRoom createChatRoom(String name, Long userId) {

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with id " + userId);
        }

        User user = userOptional.get();

        ChatRoom existingChatRoom = chatRoomRepository.findByName(name);
        if (existingChatRoom != null) {
            throw new IllegalArgumentException("Chat room with the same name already exists.");
        }
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(name);
        chatRoom.addUser(user);
        return chatRoomRepository.save(chatRoom);
    }

    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    public List<ChatRoom> getChatRoomsByUserId(Long userId) { return chatRoomRepository.findByUserId(userId); }
}