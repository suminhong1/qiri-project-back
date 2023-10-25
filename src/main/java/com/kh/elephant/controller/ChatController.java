package com.kh.elephant.controller;

import com.kh.elephant.domain.*;
import com.kh.elephant.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class ChatController {

    @Autowired
    private ChatRoomService crService;
    @Autowired
    private UserChatRoomInfoService ucriService;
    @Autowired
    private ChatMessageService cmService;
    @Autowired
    private UserInfoService uiService;
    @Autowired
    private ChatService chatService;
    @Autowired
    private PostService postService;


    // 내가 참여중인 채팅 리스트
    @GetMapping("/public/chatRooms/{id}")
    public ResponseEntity<List<UserChatRoomInfo>> findByUserId(@PathVariable String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ucriService.findByUserId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //채팅방 접속, 채팅방의 채팅보기
    @GetMapping("/chat/room/{id}")
    public ResponseEntity<ChatRoom> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(crService.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //채팅 내용 저장
    @PostMapping("/chat/save")
    public ResponseEntity<ChatMessage> createMsg(@RequestBody ChatDTO dto) {
        UserInfo userInfo = uiService.findByNickname(dto.getNickName());
        ChatRoom chatRoom = crService.show(dto.getChatRoomSEQ());
        try {
            ChatMessage chatMessage = ChatMessage.builder()
                    .userInfo(userInfo)
                    .chatRoom(chatRoom)
                    .messageContent(dto.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(cmService.create(chatMessage));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //채팅방 정보 저장
    @PostMapping("/chatroom/save")
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody ChatDTO dto) {
        Post post = postService.show(dto.getPostSEQ());
        try {
            ChatRoom chatRoom = ChatRoom.builder()
                    .post(post)
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(crService.create(chatRoom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //채팅방 나가기와 채팅방에 아무도 남아있지 않다면 해당 채팅방 관련 데이터 삭제
    @PutMapping("/chatroom/leave/{id}/{code}")
    public ResponseEntity<UserChatRoomInfo> chatRoomLeave(@PathVariable String id, int code) {
        try {
            int result = ucriService.chatRoomLeave(id, code);
            chatService.leaveChatRoom(code);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            log.info("delete error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }




}