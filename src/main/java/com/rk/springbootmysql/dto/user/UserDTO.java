package com.rk.springbootmysql.dto.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {

    private Long userId;
    private String username;
    private String email;

    public UserDTO() {}

    public UserDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public UserDTO(Long userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }
}
