package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
public class UserChatRoomInfo {
    @Id
    @Column(name = "USER_CHATROOM_INFO_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "userChatRoomInfoSequence")
    @SequenceGenerator(name = "userChatRoomInfoSequence", sequenceName = "SEQ_USER_CHATROOM_INFO", allocationSize = 1)
    private int userChatRoomInfoSeq;

    @ManyToOne
    @JoinColumn(name = "CHATROOM_SEQ")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserInfo userInfo;
}
