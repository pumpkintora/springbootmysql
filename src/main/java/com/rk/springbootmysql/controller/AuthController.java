package com.rk.springbootmysql.controller;

import com.rk.springbootmysql.dto.auth.LoginRequest;
import com.rk.springbootmysql.dto.auth.LoginResponse;
import com.rk.springbootmysql.dto.auth.SignUpRequest;
import com.rk.springbootmysql.dto.user.UserDTO;
import com.rk.springbootmysql.exception.auth.LoginException;
import com.rk.springbootmysql.exception.auth.SignupException;
import com.rk.springbootmysql.utility.JwtUtil;
import com.rk.springbootmysql.helper.JwtHelper;
import com.rk.springbootmysql.model.user.User;
import com.rk.springbootmysql.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody Map<String, String> signupRequest) {
        String username = signupRequest.get("username");
        String email = signupRequest.get("email");
        String password = signupRequest.get("password");

        if (username == null || email == null || password == null) {
            throw new SignupException("Missing signup data or incorrect format");
        }

        User checkUser = userService.findUserByEmail(email);
        if (checkUser != null) {
            throw new SignupException("User with the email address " + email + " already exists.");
        }

        userService.createUser(username, email, password);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        // Perform validation
        if (email == null || password == null) {
            throw new LoginException("Missing login data");
        }

        boolean isAuthenticated = userService.authenticateUser(email, password);

        if (!isAuthenticated) {
            throw new LoginException("Invalid login credentials");
        }

        User user = userService.findUserByEmail(email);

        if (user == null) {
            throw new LoginException("User does not exist");
        }

        UserDTO userDTO = new UserDTO(user.getUserId(), user.getUsername(), user.getEmail());
        String accessToken = JwtHelper.generateToken(email);
        return ResponseEntity.ok(new LoginResponse(userDTO, accessToken));
    }
}
