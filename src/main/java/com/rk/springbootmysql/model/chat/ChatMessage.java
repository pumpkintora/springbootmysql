package com.rk.springbootmysql.model.chat;

import com.rk.springbootmysql.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatmessageId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "chatroom_id", referencedColumnName = "chatroomId")
    private ChatRoom chatroom;

    private String content;

    private LocalDateTime timestamp;
}