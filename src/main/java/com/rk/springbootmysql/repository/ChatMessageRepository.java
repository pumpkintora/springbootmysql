package com.rk.springbootmysql.repository;

import com.rk.springbootmysql.model.chat.ChatMessage;
import com.rk.springbootmysql.model.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatRoom, Long> {

    @Query("SELECT cm FROM ChatMessage cm JOIN cm.user u WHERE u.id = :userId")
    List<ChatMessage> findByUserId(@Param("userId") Long userId);

    @Query("SELECT cm FROM ChatMessage cm JOIN cm.chatroom cr WHERE cr.id = :chatroomId")
    List<ChatMessage> findByChatroomId(@Param("chatroomId") Long chatroomId);
}
