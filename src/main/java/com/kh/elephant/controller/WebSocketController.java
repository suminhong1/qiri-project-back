package com.kh.elephant.controller;

import com.kh.elephant.domain.ChatDTO;

import com.kh.elephant.domain.ChatMessage;
import com.kh.elephant.domain.ChatRoom;
import com.kh.elephant.domain.UserInfo;
import com.kh.elephant.service.ChatMessageService;
import com.kh.elephant.service.ChatRoomService;
import com.kh.elephant.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

@Slf4j
@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatMessageService cmService;
    @Autowired
    private UserInfoService uiService;
    @Autowired
    private ChatRoomService crService;

    @Transactional
    @MessageMapping("/chat/message")
    public void message(ChatDTO dto) {
        messagingTemplate.convertAndSend("/sub/chat/room/" + dto.getChatRoomSEQ(), dto);
        UserInfo userInfo = uiService.findByNickname(dto.getNickname());
        ChatRoom chatRoom = crService.show(dto.getChatRoomSEQ());
        try {
            ChatMessage chatMessage = ChatMessage.builder()
                    .userInfo(userInfo)
                    .chatRoom(chatRoom)
                    .message(dto.getMessage())
                    .build();
            cmService.create(chatMessage);
        } catch (Exception e) {
            log.info("메세지 db저장 오류");
        }
    }
}
