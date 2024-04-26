package com.rk.springbootmysql.service;
import com.rk.springbootmysql.dto.SignUpRequest;
import com.rk.springbootmysql.model.user.User;
import jakarta.validation.Valid;

public interface UserService {

    void signup(SignUpRequest requestDto) throws Exception;

    User findUserByEmail(String email);

    User findUserByMobile(String mobileTelephone);

    User updateProfile(User user);

    User changePassword(User user, String newPassword);
}
