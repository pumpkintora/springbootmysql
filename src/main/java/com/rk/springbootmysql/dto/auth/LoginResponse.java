package com.rk.springbootmysql.dto.auth;

import com.rk.springbootmysql.dto.user.UserDTO;

public record LoginResponse(
        UserDTO user,
        String accessToken
) {
}
