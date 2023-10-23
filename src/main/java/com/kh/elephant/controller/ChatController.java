package com.kh.elephant.controller;

import com.kh.elephant.domain.ChatMessage;
import com.kh.elephant.domain.ChatRoom;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class ChatController {

//    private SimpMessageSendingOperations messagingTemplate;
//
//    @MessageMapping("/chat/message")
//    public void message(ChatMessage message) {
//        if(ChatMessage.MessageType.ENTER.equals(message.getType()))
//            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
//        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
//    }
}