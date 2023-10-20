package com.kh.elephant.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.elephant.domain.ChatMessage;
import com.kh.elephant.domain.ChatRoom;
import com.kh.elephant.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Slf4j
@Component
@RequiredArgsConstructor
public class WebSockChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;

    private final ChatService chatService;

    @Override
    protected  void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payloal {}", payload);
//        TextMessage textMessage = new TextMessage("welcom chat server");
//        session.sendMessage(textMessage);
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
        room.handleActions(session, chatMessage, chatService);
    }
}