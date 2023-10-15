package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
public class ChatRoom {
    @Id
    @Column(name = "chatroom_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "chatRoomSequence")
    @SequenceGenerator(name = "chatRoomSequence", sequenceName = "SEQ_CHATROOM", allocationSize = 1)
    private int chatRoomSeq;

    @ManyToOne
    @JoinColumn(name = "post_seq")
    private Post post;

    @Column(name = "CREATED_TIME")
    private Date createdTime;

    @Column(name = "ENDED")
    private String ended;

}




