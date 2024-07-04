package com.rk.springbootmysql.service;

import com.rk.springbootmysql.dto.chat.ChatMessageDTO;
import com.rk.springbootmysql.model.chat.ChatMessage;
import com.rk.springbootmysql.model.chat.ChatRoom;
import com.rk.springbootmysql.model.user.User;
import com.rk.springbootmysql.repository.ChatMessageRepository;
import com.rk.springbootmysql.repository.ChatRoomRepository;
import com.rk.springbootmysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public List<ChatMessage> getChatMessagesByChatroomId(Long chatroomId) {
        return chatMessageRepository.findByChatroomId(chatroomId);
    }

    public List<ChatMessage> getChatMessagesByUserId(Long userId) {
        return chatMessageRepository.findByUserId(userId);
    }

    public ChatMessage createChatMessage(ChatMessageDTO messageDTO) {
        Optional<User> userOptional = userRepository.findById(messageDTO.getUserId());
        Optional<ChatRoom> chatroomOptional = chatRoomRepository.findById(messageDTO.getChatroomId());

        if (userOptional.isEmpty() || chatroomOptional.isEmpty()) {
            throw new RuntimeException("Not found with id");
        }
        User user = userOptional.get();
        ChatRoom chatroom = chatroomOptional.get();

        ChatMessage chatMessage = new ChatMessage().setUser(user).setChatroom(chatroom).setContent(messageDTO.getContent()).setTimestamp(messageDTO.getTimestamp());

        return chatMessageRepository.save(chatMessage);
    }
}
