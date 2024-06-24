package com.rk.springbootmysql.model.chat;
import com.rk.springbootmysql.model.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "chat_rooms")
public class ChatRoom {
    // Getters and setters
    @Id
    private Long chatRoomId;
    private String name;

    @ManyToMany
    private Set<User> users = new HashSet<>();
}
