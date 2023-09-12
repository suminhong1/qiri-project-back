package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class ChatUserInfo {

    @Id
    @Column(name = "CHAT_USER_INFO_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chatUserInfoSequence")
    @SequenceGenerator(name = "chatUserInfoSequence", sequenceName = "CHAT_USER_INFO_SEQ", allocationSize = 1)
    private int chatUserInfoSeq;

    @ManyToOne
    @JoinColumn(name = "CHATROOM_SEQ")
    private int chatroomSeq;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private String userId;

    @Column(name = "READ_STATUS")
    private String readStatus;
}