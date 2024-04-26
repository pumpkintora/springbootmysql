package com.rk.springbootmysql.dto;

public record LoginResponse(
        String email,
        String token
) {
}
