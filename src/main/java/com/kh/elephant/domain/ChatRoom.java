package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class ChatRoom {

    @Id
    @Column(name = "CHATROOM_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chatroomSequence")
    @SequenceGenerator(name = "chatroomSequence", sequenceName = "SEQ_CHATROOM", allocationSize = 1)
    private int chatroomSeq;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "LAST_CHAT")
    private Date lastChat;

    @Column(name = "DELETE_CHATROOM")
    private String deleteChatroom;

}
