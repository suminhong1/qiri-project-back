package com.kh.elephant.controller;

import com.kh.elephant.domain.*;
import com.kh.elephant.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    private NotificationMessageService nmService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;



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


    //채팅방 나가기와 채팅방에 아무도 남아있지 않다면 해당 채팅방 관련 데이터 삭제
    @PutMapping("/chatroom/leave")
    public ResponseEntity<UserChatRoomInfo> chatRoomLeave(@RequestBody ChatDTO dto) {
        UserInfo userInfo = uiService.show(dto.getId());
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

    //참여메세지 관련
    @PutMapping("/chatroom/user/join")
    public ResponseEntity<UserChatRoomInfo> joinMessage(@RequestBody ChatDTO dto) {
        try {
            int result = ucriService.joinMessage(dto.getId(), dto.getChatRoomSEQ());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //내가 참여하는 채팅방 생성(매칭 신청시 생성됨)
    @PostMapping("/chatroom/join")
    public ResponseEntity<ChatRoom> joinChatRoom(@RequestBody ChatDTO dto) {
        try {
            // 채팅방 생성
            ChatRoom chatRoom = ChatRoom.builder()
                    .post(postService.show(dto.getPostSEQ()))
                    .build();
            ChatRoom result = crService.create(chatRoom);

            // 생성한 채팅방 seq 이용해여 유저채팅정보 생성(어느 채팅방에 접속해 있는지)
            UserChatRoomInfo userChatRoomInfo = UserChatRoomInfo.builder()
                    .chatRoom(crService.show(result.getChatRoomSEQ()))
                    .userInfo(uiService.show(dto.getId()))
                    .build();

            ucriService.create(userChatRoomInfo);

            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 매칭신청자의 채팅방 접속
    @PostMapping("/chatroom/enter")
    public ResponseEntity<UserChatRoomInfo> enterChatRoom(@RequestBody ChatDTO dto) {
        try {
            // 신청자의 아이디와 신청한 매칭글 정보를 통해 채팅방 검색
            UserChatRoomInfo result = ucriService.findByPostSEQAndId(dto.getPostSEQ(), dto.getApplicantId());

            // 해당 채팅방에 접속(db에 저장)
            UserChatRoomInfo userChatRoomInfo = UserChatRoomInfo.builder()
                    .userInfo(uiService.show(dto.getId()))
                    .chatRoom(result.getChatRoom())
                    .build();

            ucriService.create(userChatRoomInfo);

            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 매칭 승락한 사람이 모두 접속한 채팅방 생성
    @PostMapping("/groupChat")
    public ResponseEntity<ChatRoom> createGroupChat(@RequestBody ChatDTO dto) {
        try {
            UserInfo userInfo = uiService.show(dto.getId());
            Post post = postService.show(dto.getPostSEQ());

            // 채팅방 생성
            ChatRoom chatRoom = ChatRoom.builder()
                    .post(post)
                    .build();
            ChatRoom result = crService.create(chatRoom);

            // 채팅방 접속
            UserChatRoomInfo userChatRoomInfo = UserChatRoomInfo.builder()
                    .chatRoom(crService.show(result.getChatRoomSEQ()))
                    .userInfo(userInfo)
                    .build();
            ucriService.create(userChatRoomInfo);

            // 승락한 사람들 구하기
            List<MatchingUserInfo> matchingUserInfoList = muiService.findAccept(dto.getPostSEQ());
            
            // 승락한 사람들 초대
            for(MatchingUserInfo matchingUserInfo : matchingUserInfoList) {
                // 생성한 채팅방 seq 이용해여 유저채팅정보 생성(어느 채팅방에 접속해 있는지)
                userChatRoomInfo = UserChatRoomInfo.builder()
                        .chatRoom(crService.show(result.getChatRoomSEQ()))
                        .userInfo(matchingUserInfo.getUserInfo())
                        .build();
                        ucriService.create(userChatRoomInfo);
                
                        //알림 db 저장
                        NotificationMessage notificationMessage = NotificationMessage.builder()
                                .userInfo(matchingUserInfo.getUserInfo())
                                .message("게시글 " + post.getPostTitle() + "의 그룹채팅방에 초대되었습니다.")
                                .build();
                        nmService.create(notificationMessage);

                        // 웹소켓으로 알림 전송
                messagingTemplate.convertAndSend("/sub/notification" + matchingUserInfo.getUserInfo().getUserId() , notificationMessage);
            }

            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }



}