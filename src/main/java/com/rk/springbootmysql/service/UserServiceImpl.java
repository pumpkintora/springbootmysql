package com.rk.springbootmysql.service;

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
public class UserServiceImpl implements UserService{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepo;

    @Override
    public User signup(User user) {
        User checkUser = userRepo.findByEmail(user.getEmail());
        if (checkUser == null) {
            user = new User()
                    .setEmail(user.getEmail())
                    .setPassword(bCryptPasswordEncoder.encode(user.getPassword()))
                    .setUsername(user.getUsername())
                    .setTelephoneMobile(user.getTelephoneMobile());
            return user;
        }
        return null;
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
