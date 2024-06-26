package com.rk.springbootmysql.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rk.springbootmysql.model.chat.ChatRoom;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username")
    private String username;
    
    @Column(name = "email")
    private String email;

    private String password;

    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private Set<ChatRoom> chatrooms = new HashSet<>();
}

