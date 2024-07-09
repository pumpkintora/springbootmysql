package com.rk.springbootmysql.dto.chat;

import com.rk.springbootmysql.dto.user.UserDTO;
import com.rk.springbootmysql.model.chat.ChatMessage;
import com.rk.springbootmysql.model.chat.ChatRoom;
import com.rk.springbootmysql.model.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ChatRoomDTO {

    private Long chatroomId;
    private String name;
    private List<HashMap<String, String>> users = new ArrayList<>();
    private List<HashMap<String, String>> chatMessages = new ArrayList<>();

    public ChatRoomDTO(ChatRoom chatRoom) {
        this.chatroomId = chatRoom.getChatroomId();
        this.name = chatRoom.getName();
        for (User user : chatRoom.getUsers()) {
            HashMap<String, String> u = new HashMap<>();
            u.put("userId", user.getUserId().toString());
            u.put("username", user.getUsername());
            this.users.add(u);
        }
        for (ChatMessage chatMessage : chatRoom.getChatMessages()) {
            HashMap<String, String> cm = new HashMap<>();
            cm.put("chatmessageId", chatMessage.getChatmessageId().toString());
            cm.put("userId", chatMessage.getUser().getUserId().toString());
            cm.put("content", chatMessage.getContent());
            cm.put("timestamp", chatMessage.getTimestamp() != null ? chatMessage.getTimestamp().toString() : "");
            this.chatMessages.add(cm);
        }
    }
}
