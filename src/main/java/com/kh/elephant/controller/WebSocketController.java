//package com.kh.elephant.controller;
//
//import com.kh.elephant.domain.ChatMessage;
//import lombok.RequiredArgsConstructor;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@CrossOrigin(origins = {"*"}, maxAge = 6000)
//public class WebSocketController {
//
//    private final SimpMessagingTemplate simpMessagingTemplate;
//
//    @MessageMapping("/chat/message")
//    public void sendMessage(ChatMessage chatMessage, SimpMessageHeaderAccessor accessor) {
//        simpMessagingTemplate.convertAndSend("/sub/chat/" + chatMessage.getChatRoom().getChatRoomSEQ(), chatMessage);
//    }
//}
