package com.kh.elephant.domain;

import com.kh.elephant.service.ChatService;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.web.socket.WebSocketSession;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@DynamicInsert
public class ChatRoom {
//    @Id
//    @Column(name = "chatroom_seq")
//    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "chatRoomSequence")
//    @SequenceGenerator(name = "chatRoomSequence", sequenceName = "SEQ_CHATROOM", allocationSize = 1)
//    private int chatRoomSeq;
//
//    @ManyToOne
//    @JoinColumn(name = "post_seq")
//    private Post post;
//
//    @Column(name = "CREATED_TIME")
//    private Date createdTime;
//
//    @Column(name = "ENDED")
//    private String ended;

    private String roomId;

    private String name;

    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
        if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
            sessions.add(session);
            chatMessage.setMessage((chatMessage.getSender() + "님이 입장했습니다."));
        }
        sendMessage(chatMessage, chatService);
    }


    public <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }


}