package com.rk.springbootmysql.service;

import com.rk.springbootmysql.dto.auth.SignUpRequest;
import com.rk.springbootmysql.dto.user.UserDTO;
import com.rk.springbootmysql.repository.UserRepository;
import com.rk.springbootmysql.model.user.User;
import org.apache.catalina.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void signup(SignUpRequest request) throws Exception {
        User checkUser = userRepo.findByEmail(request.email());
        if (checkUser != null) {
            throw new Exception(String.format("User with the email address '%s' already exists.", request.email()));
        }
        User newUser = new User()
                .setEmail(request.email())
                .setPassword(bCryptPasswordEncoder.encode(request.password()))
                .setUsername(request.username());
        userRepo.save(newUser);
    }

    @Override
    public void authenticateUser(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
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
