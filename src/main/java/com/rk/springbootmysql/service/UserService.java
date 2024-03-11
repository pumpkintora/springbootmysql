package com.rk.springbootmysql.service;
import com.rk.springbootmysql.model.user.User;
public interface UserService {

    User signup(User user);

    User findUserByEmail(String email);

    User findUserByMobile(String mobileTelephone);

    User updateProfile(User user);

    User changePassword(User user, String newPassword);
}
