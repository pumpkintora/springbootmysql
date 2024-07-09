package com.rk.springbootmysql.dto.chat;

import com.rk.springbootmysql.dto.user.UserDTO;
import com.rk.springbootmysql.model.chat.ChatMessage;
import com.rk.springbootmysql.model.chat.ChatRoom;
import com.rk.springbootmysql.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ChatMessageDTO {
    private Long chatmessageId;
    private Long chatroomId;
    private Long userId;
    private String content;
    private LocalDateTime timestamp;
    public ChatMessageDTO(ChatMessage chatMessage) {
        this.chatmessageId = chatMessage.getChatmessageId();
        this.userId = chatMessage.getUser().getUserId();
        this.content = chatMessage.getContent();
        this.timestamp = chatMessage.getTimestamp();
    }

    @Override
    public String toString() {
        return "{chatmessageId:" + this.getChatmessageId() +
                "userId:" + this.getUserId() +
                "content:" + this.getContent() +
                "timestamp" + this.getTimestamp().toString() +
                "}";
    }
}
