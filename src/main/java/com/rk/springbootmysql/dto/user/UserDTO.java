package com.rk.springbootmysql.dto.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {

    private String username;
    private String email;

    public UserDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
