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

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "send_time")
    private Date sendTime;
}
