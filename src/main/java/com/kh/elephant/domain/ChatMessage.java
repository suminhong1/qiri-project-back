package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
public class ChatMessage {
    @Id
    @Column(name = "chatmessage_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "chatMessageSequence")
    @SequenceGenerator(name = "chatMessageSequence", sequenceName = "SEQ_CHATMESSAGE", allocationSize = 1)
    private int chatMessageSeq;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

    @ManyToOne
    @JoinColumn(name = "CHATROOM_SEQ")
    private ChatRoom chatRoom;

    @Lob // BLOB 데이터를 저장하기 위한 어노테이션
    @Column(name = "MSS_CONTENT")
    private byte[] messageContent;



//    public enum MessageType {
//        ENTER, TALK
//    }
//
//    private MessageType type; // 메시지 타입
//
//    private String roomId;
//
//    private String sender;
//
//    private String message;
}
