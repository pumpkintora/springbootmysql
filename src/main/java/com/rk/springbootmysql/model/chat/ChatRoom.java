package com.rk.springbootmysql.model.chat;
import com.rk.springbootmysql.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table
public class ChatRoom {
    // Getters and setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatroomId;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "chatroom_user",
            joinColumns = @JoinColumn(name = "chatroom_id", referencedColumnName = "chatroomId"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId")
    )
    private Set<User> users;

    @OneToMany(mappedBy = "chatroom")
    private Set<ChatMessage> chatMessages;

    // Default constructor
    public ChatRoom() {}

    // Getters and setters


    public void addUser(User user) {
        this.users.add(user);
        user.getChatrooms().add(this);
    }
}
