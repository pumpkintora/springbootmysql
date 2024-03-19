package com.rk.springbootmysql.controller;
import com.rk.springbootmysql.model.user.User;
import com.rk.springbootmysql.repository.UserRepository;
import com.rk.springbootmysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/")
    public String getAdmin() {
        return "get admin";
    }
}
