package com.rk.springbootmysql.repository;

import com.rk.springbootmysql.model.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    ChatRoom findByName(String name);

    @Query("SELECT cr FROM ChatRoom cr JOIN cr.users u WHERE u.id = :userId")
    List<ChatRoom> findByUserId(@Param("userId") Long userId);
}
