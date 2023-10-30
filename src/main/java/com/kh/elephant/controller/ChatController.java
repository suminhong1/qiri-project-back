package com.kh.elephant.controller;

import com.kh.elephant.domain.*;
import com.kh.elephant.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
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
    @Autowired
    private MatchingUserInfoService muiService;
    @Autowired
    private PlatformTransactionManager transactionManager;



    // 내가 참여중인 채팅방 리스트
    @GetMapping("/public/chatRooms/{id}")
    public ResponseEntity<List<UserChatRoomInfo>> findByUserId(@PathVariable String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ucriService.findByUserId(id));
        } catch (Exception e) {
            return null;
        }
    }

    // 참여중인 채팅방의 내 참여정보 가져오기
    @Transactional
    @GetMapping("/public/chatRoomInfo/{userId}/{code}")
    public ResponseEntity<UserChatRoomInfo> findByUserId(@PathVariable String userId, @PathVariable int code) {
        try {
            UserChatRoomInfo userChatRoomInfo = ucriService.findByIdAndChatRoomSEQ(code, userId);
            return ResponseEntity.status(HttpStatus.OK).body(userChatRoomInfo);
        } catch (Exception e) {
            return null;
        }
    }

    //채팅방 접속
    @GetMapping("/chat/room/{id}")
    public ResponseEntity<ChatRoom> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(crService.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //채팅방의 채팅보기
    @GetMapping("/chat/room/message/{id}")
    public ResponseEntity<List<ChatMessage>> messageFindByChatroomSEQ(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cmService.messageFindByChatroomSEQ(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    // 채팅방 생성
    @PostMapping("/chatroom/create")
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody ChatDTO dto) {
        try {
            ChatRoom chatRoom = ChatRoom.builder()
                    .post(postService.show(dto.getPostSEQ()))
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(crService.create(chatRoom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //매칭신청시 저장될 정보
    @PostMapping("/matching/{postSEQ}")
    public ResponseEntity<MatchingUserInfo> create(@PathVariable int postSEQ, @RequestBody String id) {
        try {
            MatchingUserInfo matchingUserInfo = MatchingUserInfo.builder()
                    .userInfo(uiService.show(id))
                    .post(postService.show(postSEQ))
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(muiService.create(matchingUserInfo));
        } catch (Exception e) {
            log.info("delete error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //채팅방 나가기와 채팅방에 아무도 남아있지 않다면 해당 채팅방 관련 데이터 삭제
    @PutMapping("/chatroom/leave")
    public ResponseEntity<UserChatRoomInfo> chatRoomLeave(@RequestBody ChatDTO dto) {
        UserInfo userInfo = uiService.findByNickname(dto.getNickname());
        try {
            // 채팅방 나가기(UPDATE쿼리문)
            int result = ucriService.chatRoomLeave(userInfo.getUserId(), dto.getChatRoomSEQ());
            //채팅방에 아무도 남아있지 않다면 해당 채팅방 관련 데이터 삭제(SELECT쿼리문, DELETE쿼리문)
            if(result > 0) {
                chatService.leaveChatRoom(dto.getChatRoomSEQ());
            }
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            log.info("delete error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //채팅방에 참여중인 유저목록 가져오기
    @GetMapping("/chatroom/userlist/{code}")
    public ResponseEntity<List<UserChatRoomInfo>> findByChatRoomSEQ(@PathVariable int code) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ucriService.findByUserChatRoomSEQ(code));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }






}