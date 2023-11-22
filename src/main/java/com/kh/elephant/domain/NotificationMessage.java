package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert
@Table(name = "notification_message")
public class NotificationMessage {

    @Id
    @Column(name = "NOTIFICATION_MESSAGE_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "notificationMessageSequence")
    @SequenceGenerator(name = "notificationMessageSequence", sequenceName = "SEQ_NOTIFICATION_MESSAGE", allocationSize = 1)
    private int notificationMessageSEQ;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserInfo userInfo;

    @Column(name = "message")
    private String message;

    @Column(name = "sent_time")
    private Date sentTime;

    @Column(name = "is_read")
    private String isRead;


    @Column(name = "notify_url")
    private String notifyURL;
}
