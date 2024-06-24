package com.rk.springbootmysql.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Collection;
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "user",
        indexes = @Index(
                name = "idx_user_email",
                columnList = "email",
                unique = true
        ))
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;
    
    @Column(name = "email")
    private String email;

    private String password;

    @Column(name = "telephone_mobile")
    private String telephoneMobile;
}

