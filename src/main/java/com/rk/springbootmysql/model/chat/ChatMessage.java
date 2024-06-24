package com.rk.springbootmysql.model.chat;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    @Id
    private Long messageId;
    private Long userId;
    private Long chatRoomId;
    private String content;
    private LocalDateTime timestamp;
}