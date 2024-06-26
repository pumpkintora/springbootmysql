package com.rk.springbootmysql.controller;

import com.rk.springbootmysql.dto.auth.LoginRequest;
import com.rk.springbootmysql.dto.auth.LoginResponse;
import com.rk.springbootmysql.dto.auth.SignUpRequest;
import com.rk.springbootmysql.dto.user.UserDTO;
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
    public ResponseEntity<Void> signup(@Valid @RequestBody SignUpRequest request) throws Exception {
        userService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        userService.authenticateUser(request.email(), request.password());
        User user = userService.findUserByEmail(request.email());
        UserDTO userDTO = new UserDTO(user.getUsername(), user.getEmail());
        String accessToken = JwtHelper.generateToken(request.email());
        return ResponseEntity.ok(new LoginResponse(userDTO, accessToken));
    }
}
