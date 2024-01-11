package com.kh.elephant.controller;

import com.kh.elephant.domain.*;

import com.kh.elephant.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

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
    @Autowired
    private UserChatRoomInfoService ucriService;
    @Autowired
    private NotificationMessageService nmService;

    // 채팅 전송 및 db저장, 알림 처리
    @Transactional
    @MessageMapping("/chat/message")
    public void message(ChatDTO dto) {
        // 채팅메세지 웹소켓으로 전송
        messagingTemplate.convertAndSend("/sub/chat/room/" + dto.getChatRoomSEQ(), dto);

        UserInfo userInfo = uiService.findByNickname(dto.getNickname());
        ChatRoom chatRoom = crService.show(dto.getChatRoomSEQ());
        try {
            ChatMessage chatMessage = ChatMessage.builder()
                    .userInfo(userInfo)
                    .chatRoom(chatRoom)
                    .message(dto.getMessage())
                    .build();
            // 채팅메세지 db저장
            cmService.create(chatMessage);
        } catch (Exception e) {
            log.error("메세지 db저장 오류");
        }

        // 채팅 알림처리
        List<UserChatRoomInfo> userChatRoomInfoList = ucriService.findByUserChatRoomSEQ(dto.getChatRoomSEQ());
        for(UserChatRoomInfo user : userChatRoomInfoList) {
            try {
                // 발송자는 해당 채팅에 대한 알림 제외 처리
                if(!user.getUserInfo().getUserId().equals(userInfo.getUserId())) {

                    // 한 채팅방에 대한 알림은 확인전까지 한번만
                    int isDuplicated = nmService.checkDuplicateNotify(user.getUserInfo().getUserId(), user.getChatRoom().getChatRoomSEQ());
                    if (isDuplicated == 0) {
                        // 채팅알림 db저장
                        NotificationMessage notificationMessage = NotificationMessage.builder()
                                .userInfo(user.getUserInfo())
                                .message(user.getChatRoom().getPost().getPostTitle() + "의 채팅방에서 새 메세지가 도착했습니다.")
                                .chatRoom(user.getChatRoom())
                                .post(user.getChatRoom().getPost())
                                .build();
                        nmService.create(notificationMessage);
                        // 채팅알림 웹소켓 전송
                        messagingTemplate.convertAndSend("/sub/notification/" + user.getUserInfo().getUserId(), notificationMessage);
                    }
                }
            } catch (Exception e) {
                log.error("채팅메시지알림 db저장 오류");
            }
        }
    }


}