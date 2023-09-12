package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Entity
public class ChatInfo {

    @Id
    @Column(name = "CHAT_INFO_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chatInfoSequence")
    @SequenceGenerator(name = "chatInfoSequence", sequenceName = "SEQ_CHAT_INFO", allocationSize = 1)
    private int chatInfoSeq;

    @ManyToOne
    @JoinColumn(name = "CHATROOM_SEQ")
    private int chatroomSeq;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private String userId;

    @Column(name = "CHAT_CONTENT")
    private String chatContent;

    @Column(name = "CHAT_SENT_TIME")
    private Timestamp chatSentTime;
}