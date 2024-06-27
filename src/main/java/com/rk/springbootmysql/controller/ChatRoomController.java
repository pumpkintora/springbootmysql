package com.rk.springbootmysql.controller;

import com.rk.springbootmysql.dto.chat.ChatRoomDTO;
import org.springframework.web.bind.annotation.*;
import com.rk.springbootmysql.model.chat.ChatRoom;
import com.rk.springbootmysql.model.user.User;
import com.rk.springbootmysql.service.ChatRoomService;
import com.rk.springbootmysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin
@RequestMapping("/api/chatroom")
public class ChatRoomController {
    private static final Logger logger = LoggerFactory.getLogger(ChatRoomController.class);
    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ChatRoomDTO> createChatRoom(String name, String email) {
        User user = userService.findUserByEmail(email);
        ChatRoom newChatRoom = chatRoomService.createChatRoom(name, user.getUserId());
        ChatRoomDTO crDTO = new ChatRoomDTO(newChatRoom);
        return ResponseEntity.ok(crDTO);
    }

    @GetMapping("/all")
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomService.getAllChatRooms();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<?>> getChatRoomsByUserId(@PathVariable Long userId, @RequestParam Boolean withUser) {
        List<ChatRoom> chatRooms = chatRoomService.getChatRoomsByUserId(userId);
        if (withUser) {
            List<ChatRoomDTO> crDTO = new ArrayList<>();
            for (ChatRoom cr : chatRooms) {
                crDTO.add(new ChatRoomDTO(cr));
            }
            return ResponseEntity.ok(crDTO);
        } else {
            List<Map<String, Object>> crDTO = new ArrayList<Map<String, Object>>();
            for (ChatRoom cr : chatRooms) {
                Map<String, Object> obj = new HashMap<>();
                obj.put("chatroomId", cr.getChatroomId());
                obj.put("name", cr.getName());
                crDTO.add(obj);
            }
            return ResponseEntity.ok(crDTO);
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }
}