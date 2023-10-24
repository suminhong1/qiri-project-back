package com.kh.elephant.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.web.socket.WebSocketSession;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "CHATROOM")
public class ChatRoom {
    @Id
    @Column(name = "CHATROOM_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "chatRoomSequence")
    @SequenceGenerator(name = "chatRoomSequence", sequenceName = "SEQ_CHATROOM", allocationSize = 1)
    private int chatRoomSEQ;

    @ManyToOne
    @JoinColumn(name = "POST_SEQ")
    private Post post;

    @Column(name = "CREATED_TIME")
    private Date createdTime;

    @Column(name = "ENDED")
    private String ended;



//    private String roomId;
//
//    private String name;
//
//    public static ChatRoom create(String name) {
//        ChatRoom chatRoom = new ChatRoom();
//        chatRoom.roomId = UUID.randomUUID().toString();
//        chatRoom.name = name;
//        return chatRoom;
//    }


}