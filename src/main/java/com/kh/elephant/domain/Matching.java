package com.kh.elephant.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "MATCHING")
public class Matching {
    @Id
    @Column(name = "MATCHING_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "matchingSequence")
    @SequenceGenerator(name = "matchingSequence", sequenceName = "SEQ_MATCHING", allocationSize = 1)

    private String matchingSeq;

    @ManyToOne
    @JoinColumn(name = "POST_SEQ")
    private String postSeq;

    @ManyToOne
    @JoinColumn(name = "CHATROOM_SEQ")
    private int chatroomSeq;

    @Column(name = "MATCHING_APPOINTMENT")
    private Date matchingAppointment;

    @Column(name = "MATCHING_RESULT")
    private String matchingResult;

    @Column(name = "MATCHING_DATE")
    private Date matchingDate;

}
