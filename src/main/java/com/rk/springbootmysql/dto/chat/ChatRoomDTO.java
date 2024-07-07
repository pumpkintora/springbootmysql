package com.rk.springbootmysql.dto.chat;

import com.rk.springbootmysql.dto.user.UserDTO;
import com.rk.springbootmysql.model.chat.ChatMessage;
import com.rk.springbootmysql.model.chat.ChatRoom;
import com.rk.springbootmysql.model.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ChatRoomDTO {
    private Long chatroomId;
    private String name;
    private List<UserDTO> users = new ArrayList<>();
    private List<ChatMessageDTO> chatMessages = new ArrayList<>();

    public ChatRoomDTO(ChatRoom chatRoom) {
        this.chatroomId = chatRoom.getChatroomId();
        this.name = chatRoom.getName();
        for (User user : chatRoom.getUsers()) {
            UserDTO u = new UserDTO();
            u.setUserId(user.getUserId());
            u.setUsername(user.getUsername());
            u.setEmail(user.getEmail());
            this.users.add(u);
        }
        for (ChatMessage chatMessage : chatRoom.getChatMessages()) {
            ChatMessageDTO cm = new ChatMessageDTO();
            cm.setUserId(chatMessage.getUser().getUserId())
                    .setChatroomId(chatMessage.getChatroom().getChatroomId())
                    .setContent(chatMessage.getContent())
                    .setTimestamp(chatMessage.getTimestamp());
            this.chatMessages.add(cm);
        }
    }
}
