package com.rk.springbootmysql.service;

import com.rk.springbootmysql.dto.SignUpRequest;
import com.rk.springbootmysql.repository.UserRepository;
import com.rk.springbootmysql.model.user.User;
import com.rk.springbootmysql.model.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepo;

    @Override
    public void signup(SignUpRequest request) throws Exception {
        User checkUser = userRepo.findByEmail(request.email());
        if (checkUser != null) {
            throw new Exception(String.format("User with the email address '%s' already exists.", request.email()));
        }
        User newUser = new User()
                .setEmail(request.email())
                .setPassword(bCryptPasswordEncoder.encode(request.password()))
                .setUsername(request.username())
                .setTelephoneMobile(request.telephoneMobile());
        userRepo.save(newUser);
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public User findUserByMobile(String mobileTelephone) {
        return null;
    }

    @Override
    public User changePassword(User user, String newPassword) {
        return null;
    }

    @Override
    public User updateProfile(User user) {
        return null;
    }
}
