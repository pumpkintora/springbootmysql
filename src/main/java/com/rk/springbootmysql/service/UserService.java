package com.rk.springbootmysql.service;
import com.rk.springbootmysql.dto.auth.SignUpRequest;
import com.rk.springbootmysql.dto.user.UserDTO;
import com.rk.springbootmysql.model.user.User;

public interface UserService {

    void signup(SignUpRequest requestDto) throws Exception;

    User findUserByEmail(String email);
    void authenticateUser(String email, String password);

    User updateProfile(User user);

    User changePassword(User user, String newPassword);
}
