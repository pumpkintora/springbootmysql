package com.rk.springbootmysql.controller;

import org.springframework.web.bind.annotation.*;
import com.rk.springbootmysql.model.chat.ChatRoom;
import com.rk.springbootmysql.model.user.User;
import com.rk.springbootmysql.service.ChatRoomService;
import com.rk.springbootmysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/chatroom")
public class ChatRoomController {
    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ChatRoom> createChatRoom(String name, String email) {
        User user = userService.findUserByEmail(email);
        ChatRoom newChatRoom = chatRoomService.createChatRoom(name, user.getUserId());
        return ResponseEntity.ok(newChatRoom);
    }

    @GetMapping("/all")
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomService.getAllChatRooms();
    }

    @GetMapping("/user/{userId}")
    public List<ChatRoom> getChatRoomsByUserId(@PathVariable Long userId) {
        return chatRoomService.getChatRoomsByUserId(userId);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }
}