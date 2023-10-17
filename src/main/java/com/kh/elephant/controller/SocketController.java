//package com.kh.elephant.controller;
//
//
//import com.kh.elephant.domain.ChatMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashSet;
//import java.util.Set;
//
//
//@RestController
//@CrossOrigin(origins = {"*"}, maxAge = 6000)
//public class SocketController {
//
//
//    private static Set<Integer> userList = new HashSet<>();
//
//    @Autowired
//    private SimpMessagingTemplate simpMessagingTemplate;
//
//    @MessageMapping("/chat/{id}")
//    public void sendMessage(@Payload ChatMessage chatMessage, @DestinationVariable Integer id){
//        this.simpMessagingTemplate.convertAndSend("/queue/addChatToClient/"+id,chatMessage);
//    }
//
//    @MessageMapping("/join")
//    public void joinUser(@Payload Integer userId){
//        userList.add(userId);
//        userList.forEach(user-> System.out.println(user));
//    }
//}
