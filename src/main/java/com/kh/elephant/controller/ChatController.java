package com.kh.elephant.controller;

import com.kh.elephant.domain.ChatDTO;
import com.kh.elephant.domain.ChatMessage;
import com.kh.elephant.domain.ChatRoom;
import com.kh.elephant.service.ChatRoomService;
import com.kh.elephant.service.UserChatRoomInfoService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/qiri/*")
public class ChatController {

    @Autowired
    private ChatRoomService crService;

    @Autowired
    private UserChatRoomInfoService ucriService;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    // 채팅 리스트 화면
    @GetMapping("/chat/rooms")
    public ResponseEntity<List<ChatRoom>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(crService.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/chat/room/{id}")
    public ResponseEntity<ChatRoom> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(crService.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @MessageMapping("/chat/message")
    public void message(ChatDTO dto) {
        messagingTemplate.convertAndSend("/sub/chat/room/" + dto.getChatRoomSEQ(), dto);
    }

}